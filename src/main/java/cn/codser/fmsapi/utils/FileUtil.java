
package cn.codser.fmsapi.utils;

import cn.codser.fmsapi.domain.doo.AppDo;
import cn.codser.fmsapi.domain.doo.FileDo;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * File工具类
 * @author codser
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 系统临时目录
     */
    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;
    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * MultipartFile转File
     */
    public static File multipartFile2File(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = "." + getExtName(fileName);
        File file = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            file = new File(SYS_TEM_DIR + IdUtil.simpleUUID() + prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return file;
    }

    /**
     * 获取文件扩展名，不带 .
     */
    public static String getExtName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoExt(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 根据文件大小获取显示格式化文件大小
     */
    public static String getShowSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }

    /**
     * inputStream 转 File
     */
    static File inputStream2File(InputStream ins, String name) throws Exception {
        File file = new File(SYS_TEM_DIR + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead;
            int len = 8192;
            byte[] buffer = new byte[len];
            while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                os.close();
            }
            if(ins!=null){
                ins.close();
            }
        }
        return file;
    }


    /**
     * 检查文件是否超大小
     * @param maxSize
     * @param size
     * @throws Exception
     */
    public static void checkSize(long maxSize, long size) throws Exception {
        // 1M
        int len = 1024 * 1024;
        if (size > (maxSize * len)) {
            throw new Exception("文件超出规定大小");
        }
    }

    /**
     * 判断两个文件是否相同
     */
    public static boolean check(File file1, File file2) throws IOException {
        String img1Md5 = getMd5(file1);
        String img2Md5 = getMd5(file2);
        if(img1Md5 != null){
            return img1Md5.equals(img2Md5);
        }
        return false;
    }

    /**
     * file转byte[]
     * @param file
     * @return
     */
    private static byte[] file2Bytes(File file) throws IOException {
        // 得到文件长度
        byte[] b = new byte[(int) file.length()];
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            try {
                System.out.println(in.read(b));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            if(in!=null){
                in.close();
            }
        }
        return b;
    }


    /**
     * 获取上传文件的md5
     * @param file
     * @return
     */
    public static String getMd5(MultipartFile file)throws Exception {
        byte[] uploadBytes = file.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(uploadBytes);
        String hashString = new BigInteger(1, digest).toString(16);
        return hashString;
    }

    public static String getMd5(File file) throws IOException {
        return getMd5(file2Bytes(file));
    }

    public static String getSha256(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return DigestUtils.sha512Hex(fileInputStream);
    }
    /**
     * 获取文件md5
     * @param bytes
     * @return
     */
    private static String getMd5(byte[] bytes) {
        // 16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(bytes);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            // 移位 输出字符串
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 检查文件
     * @param fileDo
     * @throws Exception
     */
    public static void checkAppIdAndFileId(FileDo fileDo,String fileHome) throws Exception {
        if (fileDo == null) {
            throw new Exception("下载的文件记录不存在");
        }
        if (fileDo.getEnableDownload() == 0) {
            throw new Exception("该文件被禁用下载");
        }
        if (!new File(fileHome+fileDo.getPath()).exists()) {
            throw new Exception("下载的文件实体不存在");
        }
    }


    /**
     * 根据AppDo,FileDo下载文件
     * @param request
     * @param response
     * @param fileHome
     * @param fileDo
     * @throws Exception
     */
    public static void downloadFileByAppDoAndFileDo(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    String fileHome,
                                                    FileDo fileDo) throws Exception {
        checkAppIdAndFileId(fileDo,fileHome);
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileDo.getRealName(), "UTF-8"));
            fis=new FileInputStream(fileHome+fileDo.getPath());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 获取真实保存文件路径
     * @param file
     * @param appId
     * @return
     */
    public static String getSavePath(MultipartFile file,String appId,String saveType){
        // 生成日期时间戳字符串
        String dateStr=DateUtil.format(new Date(),"yyyyMMddhhmmssS");
        // 获取文件后缀名
        String suffix = getExtName(file.getOriginalFilename());
        // 生成保存的文件名
        return File.separator+appId+File.separator+saveType+File.separator+IdUtil.fastSimpleUUID()+"_"+ dateStr + "." + suffix;
    }

    /**
     * 将文件名解析成文件的上传路径
     */
    public static File uploadMultipartFile(MultipartFile file,String fileHome, String path) throws Exception {

        // 生成保存完整绝对路径
        String savePath=fileHome+path;
        System.err.println("savePath:"+savePath);
        // 解析为完整正确的路径
        File dest = new File(savePath).getCanonicalFile();
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            // 创建目标目录
            if (!dest.getParentFile().mkdirs()) {
                throw new Exception("无法创建目标存储目录");
            }
        }
        file.transferTo(dest);
//        FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
        return dest;
    }

    /**
     * 将文件名解析成文件的上传路径
     */
    public static void removeFile(String fileHome, String path) throws Exception {
        String fileSavePath=fileHome+path;
        File dest = new File(fileSavePath).getCanonicalFile();
        if (dest.exists()) {
            dest.delete();
        }
    }
    /**
     * 将文件名解析成文件的上传路径
     */
    public static void removeFile(String fileHome, List<FileDo> files) throws Exception {
        for(FileDo file:files){
            removeFile(fileHome,file.getPath());
        }
    }

}

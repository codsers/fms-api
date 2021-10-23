/*
Navicat SQLite Data Transfer

Source Server         : fmsapi.db
Source Server Version : 30714
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30714
File Encoding         : 65001

Date: 2021-10-24 00:10:44
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for fms_apps
-- ----------------------------
DROP TABLE IF EXISTS "main"."fms_apps";
CREATE TABLE "fms_apps" (
"app_id"  TEXT NOT NULL,
"app_name"  TEXT,
"app_desc"  TEXT,
"create_time"  INTEGER,
PRIMARY KEY ("app_id")
);

-- ----------------------------
-- Table structure for fms_files
-- ----------------------------
DROP TABLE IF EXISTS "main"."fms_files";
CREATE TABLE "fms_files" (
  "file_id" text NOT NULL,
  "sha256" TEXT,
  "real_name" TEXT,
  "save_name" TEXT,
  "suffix" TEXT,
  "path" TEXT,
  "size" integer,
  "show_size" REAL,
  "app_id" TEXT,
  "cus_tag" TEXT,
  "bus_info" TEXT,
  "upload_time" TEXT,
	"enable_download" integer,
  PRIMARY KEY ("file_id")
);

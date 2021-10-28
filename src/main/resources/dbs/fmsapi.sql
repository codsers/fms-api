
CREATE TABLE IF NOT EXISTS  `fms_apps` (
`app_id` TEXT NOT NULL,
`app_name`  TEXT,
`app_desc`  TEXT,
`create_time`  INTEGER,
PRIMARY KEY (`app_id`)
);

CREATE TABLE IF NOT EXISTS  `fms_files` (
  "file_id" text NOT NULL,
  "app_id" TEXT NOT NULL,
  "sha256" TEXT,
  "real_name" TEXT,
  "save_name" TEXT,
  "suffix" TEXT,
  "path" TEXT,
  "size" integer,
  "show_size" REAL,
  "cus_tag" TEXT,
  "bus_info" TEXT,
  "upload_time" TEXT,
  "enable_download" integer,
  PRIMARY KEY ("file_id", "app_id")
);
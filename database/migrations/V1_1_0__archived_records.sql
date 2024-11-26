CREATE TABLE `archived_records` (
	`archived_records_id` INT NOT NULL AUTO_INCREMENT,
	`record_type` VARCHAR(255) NOT NULL,
	`moved_at` TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
	`permanent_deleted_at` TIMESTAMP NULL DEFAULT NULL,
	`additional_information` VARCHAR(255) NULL DEFAULT NULL,
	`modified_user` VARCHAR(255) NOT NULL,
	`modified_user_id` INT NULL DEFAULT NULL,
	PRIMARY KEY (`archived_records_id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `archived_records` (
	`archived_record_id` INT NOT NULL AUTO_INCREMENT,
	`record_type` VARCHAR(255) NOT NULL,
	`original_record_id` INT NOT NULL,
	`moved_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`permanent_deleted_at` TIMESTAMP NULL,
	`additional_information` VARCHAR(255) NULL,
	`modified_user` VARCHAR(255) NOT NULL,
	`modified_user_id` INT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`archived_records_id`),
	FOREIGN KEY (`modified_user_id`) REFERENCES `users`(`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;

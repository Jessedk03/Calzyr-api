CREATE TABLE `archived_records` (
	`archived_records_id` INT NOT NULL AUTO_INCREMENT,
	`record_type` VARCHAR(255) NOT NULL,
	`moved_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`permanent_deleted_at` TIMESTAMP NULL,
	`additional_information` VARCHAR(255) NULL,
	`modified_user` VARCHAR(255) NOT NULL,
	`modified_user_id` INT NULL,
	FOREIGN KEY (`modified_user_id`) REFERENCES `users`(`user_id`)
	PRIMARY KEY (`archived_records_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;

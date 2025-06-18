CREATE TABLE IF NOT EXISTS `companies` (
    `company_id` INT NOT NULL AUTO_INCREMENT,
    `company_name` VARCHAR(255) NOT NULL,
    `coc_number` VARCHAR(255) NULL,
    `vat_number` VARCHAR(255) NULL,
    `street_name` VARCHAR(255) NOT NULL,
    `street_number` INT NOT NULL,
    `street_number_suffix` VARCHAR(10) NULL,
    `office_number` INT NULL,
    `office_floor` INT NULL,
    `city` VARCHAR(255) NULL,
    `province_state` VARCHAR(255) NULL,
    `country` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`company_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;
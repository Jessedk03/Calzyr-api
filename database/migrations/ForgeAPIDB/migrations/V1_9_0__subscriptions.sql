CREATE TABLE IF NOT EXISTS `subscriptions` (
    `subscription_id` INT NOT NULL AUTO_INCREMENT,
    `subscription_name` VARCHAR(50) NOT NULL,
    `subscription_description` VARCHAR(255) NULL,
    `base_price_monthly` DECIMAL NOT NULL,
    `base_price_yearly` DECIMAL NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`subscription_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;
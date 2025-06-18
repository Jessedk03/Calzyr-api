CREATE TABLE IF NOT EXISTS `company_subscriptions` (
    `company_subscription_id` INT NOT NULL AUTO_INCREMENT,
    `company_id` INT NOT NULL,
    `subscription_id` INT NOT NULL,
    `user_amount` INT NOT NULL,
    `price_per_user` DECIMAL NOT NULL,
    `total_price` DECIMAL NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`company_subscription_id`),
    FOREIGN KEY (`company_id`) REFERENCES `companies`(`company_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;
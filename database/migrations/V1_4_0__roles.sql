CREATE TABLE IF NOT EXISTS `roles`(
    `role_id` INT NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(50) NOT NULL,
    `role_description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`role_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;
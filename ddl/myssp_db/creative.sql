CREATE TABLE `creative` (
  `id` INT(10) unsigned NOT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `is_deliverable` ENUM('yes', 'no') DEFAULT 'yes',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO creative VALUES (1, 'creative01', 'yes', CURRENT_TIMESTAMP);
INSERT INTO creative VALUES (2, 'creative02', 'no', CURRENT_TIMESTAMP);
INSERT INTO creative VALUES (3, 'creative03', 'yes', CURRENT_TIMESTAMP);
INSERT INTO creative VALUES (4, 'creative04', 'yes', CURRENT_TIMESTAMP);

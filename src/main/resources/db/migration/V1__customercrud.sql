START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `user` (`id`, `email`, `password`, `role`, `username`) VALUES
(505, 'test@gmail.com', '$2a$10$ftihA635RXh9y7Xj9ywxJe3HZ97cuJBAkp1Yu9Z.Fg31SBDqCtVyS', 'USER', 'test'),
(506, 'sudhanshu@celerispay.com', '$2a$10$5uj4ExiMNYfJ9w4B9Q2CoubCR556vKxaewAMplXaD7TV18zLCu.Ky', 'ADMIN', 'sudhanshu'),
(507, 'shekhar@celerispay.com', '$2a$10$X0vllDelWlUpoghVOg3X5eZInocHrbWIMyufQbckdbhI9YGu7UmjS', 'USER', 'shekhar');


CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_no` varchar(255) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `transaction_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `customer` (`cust_id`, `address`, `contact_no`, `customer_name`, `transaction_id`) VALUES
(508, 'New Delhi', '8765432109', 'sudhanshu', NULL);



CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `transaction_id` varchar(800) DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `transaction_amount` bigint(20) DEFAULT NULL
  
  

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `transaction` (`id`,`transaction_id`,`transaction_date`,`client_name`,`transaction_type`, `transaction_amount`) VALUES
(509,'48EF4798-6CE7-6E2C-3273-8FB42F83DE62','2008-11-11','purchase', 'State Bank Of India', 30000);



CREATE TABLE `bank_details` (
`id` bigint(20) NOT NULL,
  `account_no` varchar(500) DEFAULT NULL,
    `bank_name` varchar(855) DEFAULT NULL,
  `acc_type` varchar(255) DEFAULT NULL,
  `available_balance` bigint(50) DEFAULT NULL,

  `customer_cust_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `bank_details` (`id`,`account_no`, `acc_type`, `available_balance`, `bank_name`, `customer_cust_id`) VALUES
(401,'SBI12345678', 'SAVING', 5000000, 'State Bank Of India', 108);

COMMIT;


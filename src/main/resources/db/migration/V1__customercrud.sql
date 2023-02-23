

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
  `client_name` varchar(255) DEFAULT NULL,
  `transaction_amount` bigint(20) DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `transaction_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--INSERT INTO `transaction` (`id`, `client_name`, `transaction_amount`, `transaction_date`, `transaction_id`, `transaction_type`) VALUES
--(509, NULL, NULL, NULL, NULL, NULL)



CREATE TABLE `bank_details` (
  `account_no` varchar(12) NOT NULL,
    `bank_name` varchar(255) DEFAULT NULL,
  `acc_type` varchar(255) DEFAULT NULL,
  `available_balance` bigint(20) DEFAULT NULL,

  `customer_cust_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `bank_details` (`account_no`, `acc_type`, `available_balance`, `bank_name`, `customer_cust_id`) VALUES
('SBI12345678', 'SAVING', 5000000, 'State Bank Of India', NULL);




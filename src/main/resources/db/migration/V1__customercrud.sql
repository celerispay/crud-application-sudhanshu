START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(90) DEFAULT NULL,
  `role` varchar(8) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact_no` varchar(15) NOT NULL  UNIQUE,
  `customer_name` varchar(15) DEFAULT NULL,
  `transaction_id` bigint(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `transaction` (
  `transaction_id` bigint(12) DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `client_name` varchar(40) DEFAULT NULL,
  `transaction_type` varchar(12) DEFAULT NULL,
  `transaction_amount` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `bank_details` (
`id` bigint(20) NOT NULL,
  `account_no` varchar(15) DEFAULT NULL,
    `bank_name` varchar(50) DEFAULT NULL,
  `acc_type` varchar(15) DEFAULT NULL,
  `available_balance` bigint(50) DEFAULT NULL,
  `customer_cust_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `demo_customer` (
  `cust_id` int(11) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact_no` varchar(15) NOT NULL  UNIQUE,
  `customer_name` varchar(15) DEFAULT NULL,
  `transaction_id` bigint(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


COMMIT;


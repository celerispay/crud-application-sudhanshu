START TRANSACTION;
SET time_zone = "+00:00";

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);


INSERT INTO `user` (`id`, `email`, `password`, `role`, `username`) VALUES
(505, 'test@gmail.com', '$2a$10$ftihA635RXh9y7Xj9ywxJe3HZ97cuJBAkp1Yu9Z.Fg31SBDqCtVyS', 'USER', 'test'),
(506, 'sudhanshu@celerispay.com', '$2a$10$5uj4ExiMNYfJ9w4B9Q2CoubCR556vKxaewAMplXaD7TV18zLCu.Ky', 'ADMIN', 'sudhanshu'),
(507, 'shekhar@celerispay.com', '$2a$10$X0vllDelWlUpoghVOg3X5eZInocHrbWIMyufQbckdbhI9YGu7UmjS', 'USER', 'shekhar');


INSERT INTO `customer` (`cust_id`, `address`, `contact_no`, `customer_name`, `transaction_id`) VALUES
(508, 'New Delhi', '8765432109', 'sudhanshu', '48EF4798-6CE7-6E2C-3273-8FB42F83DE62');

INSERT INTO `transaction` (`transaction_id`,`transaction_date`,`client_name`,`transaction_type`, `transaction_amount`) VALUES
('48EF4798-6CE7-6E2C-3273-8FB42F83DE62','2008-11-11','State Bank Of India', 'PURCHASE', 30000);


INSERT INTO `bank_details` (`id`,`account_no`, `acc_type`, `available_balance`, `bank_name`, `customer_cust_id`) VALUES
(401,'SBI12345678', 'SAVING', 5000000, 'State Bank Of India', 508);

COMMIT;


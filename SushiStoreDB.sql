-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Июн 27 2021 г., 16:48
-- Версия сервера: 10.4.14-MariaDB
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `SushiStoreDB`
--

-- --------------------------------------------------------

--
-- Структура таблицы `sets`
--

CREATE TABLE `sets` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `set_picture` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `sets`
--

INSERT INTO `sets` (`id`, `name`, `price`, `set_picture`, `amount`) VALUES
(1, 'Сет 1', 1380, '5e469cb6ee2b07049df065af9c8595ed5b643717', 14),
(2, 'Сет 2', 2200, 'adaffa2de95ecad57a7437cd3b276a7df53c5f6a', 16),
(3, 'Cет 3', 2370, '0ebdb2568f00de230212dceadae7d0882c08cf4c', 32),
(4, 'Сет 4', 2550, 'ed7fdac451311194a5649f9bafb364a255c5f755', 14),
(6, 'Найс сет', 3760, '177d38c1d7614d7a97d57762033eb0b07ee8977d', 24),
(7, 'Три самурая сет', 5310, '39228714f11f3338a61185fadbd3bc454e45d368', 27),
(8, 'Тэйсти сет', 4100, '6ef8c8637f793a646e052227a3f2b0cd59e7583d', 24),
(9, 'Вегас сет', 11700, 'e9beab96ddc8810f61917dc38a85c33c92a72b6b', 80),
(10, 'Эксклюзив сет', 6780, '394e875cbe45e645544241ffddb21f2b405bf685', 27),
(11, 'Сет 7', 2700, 'b4a512fd58752a4df532225c9110ea9d8e927020', 16),
(12, 'Вегетерианский сет', 1510, '0300f03abdb885532a0e0259a3e8abd222f2ec89', 24);

-- --------------------------------------------------------

--
-- Структура таблицы `sets_rolls_list`
--

CREATE TABLE `sets_rolls_list` (
  `sets_id` bigint(20) NOT NULL,
  `rolls_list_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `sets_rolls_list`
--

INSERT INTO `sets_rolls_list` (`sets_id`, `rolls_list_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 5),
(2, 6),
(3, 1),
(4, 5),
(4, 4),
(6, 1),
(6, 4),
(6, 6),
(7, 1),
(7, 4),
(8, 2),
(8, 3),
(9, 7),
(9, 5),
(9, 3),
(9, 8),
(9, 1),
(10, 3),
(10, 5),
(11, 12),
(11, 7),
(11, 10),
(12, 11),
(12, 14);

-- --------------------------------------------------------

--
-- Структура таблицы `sets_sushi_list`
--

CREATE TABLE `sets_sushi_list` (
  `sets_id` bigint(20) NOT NULL,
  `sushi_list_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `sets_sushi_list`
--

INSERT INTO `sets_sushi_list` (`sets_id`, `sushi_list_id`) VALUES
(1, 1),
(1, 3),
(2, 7),
(2, 10),
(3, 6),
(3, 7),
(3, 4),
(3, 3),
(4, 8),
(4, 7),
(4, 9),
(7, 1),
(7, 3),
(7, 8),
(10, 1),
(10, 8),
(10, 10);

-- --------------------------------------------------------

--
-- Структура таблицы `t_drinks`
--

CREATE TABLE `t_drinks` (
  `id` bigint(20) NOT NULL,
  `drink_picture` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_drinks`
--

INSERT INTO `t_drinks` (`id`, `drink_picture`, `name`, `price`) VALUES
(1, 'caafc9316fad069a6688649fa5fe9c2a67edae00', 'Coca-cola 1л', 350),
(2, 'fea0c48b783319217102a86b10218dbc9a02bb99', 'Sprite 0.5л', 300),
(3, '9d6260a815538b8a69159988bddada72b0fdf224', 'Coca-cola 0.5л', 300),
(4, 'ab369cbe2a7bd180bf7a7a3ccd3933ed456bd004', 'Bonaqua 1л', 250),
(5, '74eb55ffea35c8f5d37840e8f2f60d0acdedfd7d', 'Pico 1л', 500),
(6, 'b8cfc8d87e280ca8acb854e6ff66820a5a08d7a4', 'Pico mini', 200),
(7, 'f61f0a2b5bc74618ed20c25437efe5cb3420112b', 'Pepsi 1л', 350),
(9, '188f74e6ab776ee8380bd56abcdb6be5f894ad2b', 'Fuse tea 0.5л', 300);

-- --------------------------------------------------------

--
-- Структура таблицы `t_ingredients`
--

CREATE TABLE `t_ingredients` (
  `id` bigint(20) NOT NULL,
  `ingredient_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_ingredients`
--

INSERT INTO `t_ingredients` (`id`, `ingredient_name`) VALUES
(1, 'Огурцы'),
(2, 'Креветки'),
(3, 'Сыр'),
(7, 'Мак'),
(8, 'Красная икра'),
(9, 'Черная икра'),
(10, 'Лосось'),
(13, 'Угорь'),
(14, 'Унаги соус'),
(15, 'Cпайси соус'),
(16, 'Cырный соус'),
(17, 'Мидии'),
(18, 'Краб'),
(19, 'Морские водоросли'),
(20, 'Кунжут'),
(21, 'Курица');

-- --------------------------------------------------------

--
-- Структура таблицы `t_items`
--

CREATE TABLE `t_items` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `drink_picture` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `roll_picture` varchar(255) DEFAULT NULL,
  `set_picture` varchar(255) DEFAULT NULL,
  `sushi_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `t_items_ingredients`
--

CREATE TABLE `t_items_ingredients` (
  `sushi_id` bigint(20) NOT NULL,
  `ingredients_id` bigint(20) NOT NULL,
  `rolls_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `t_items_rolls_list`
--

CREATE TABLE `t_items_rolls_list` (
  `sets_id` bigint(20) NOT NULL,
  `rolls_list_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `t_items_sushi_list`
--

CREATE TABLE `t_items_sushi_list` (
  `sets_id` bigint(20) NOT NULL,
  `sushi_list_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `t_order`
--

CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL,
  `commentary` varchar(255) DEFAULT NULL,
  `entrance` varchar(255) DEFAULT NULL,
  `flat` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `house` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_order`
--

INSERT INTO `t_order` (`id`, `commentary`, `entrance`, `flat`, `floor`, `house`, `client_name`, `price`, `phone`, `street`) VALUES
(3, 'Сдача с 10к', '', '', '', '31', 'Даурбек', 1600, '+77789115835', ' село Асан, ул Алихан Букейханов'),
(4, 'Сдача с 5тыс тг', '2141', '45', '6', '55', 'Марат', 4870, '+77087772211', '6мкр');

-- --------------------------------------------------------

--
-- Структура таблицы `t_roles`
--

CREATE TABLE `t_roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_roles`
--

INSERT INTO `t_roles` (`id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `t_rolls`
--

CREATE TABLE `t_rolls` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `roll_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_rolls`
--

INSERT INTO `t_rolls` (`id`, `amount`, `name`, `price`, `roll_picture`) VALUES
(1, 8, 'Каппа маки', 370, 'c505a6ea4ec365180d8576af31e3f571007ae976'),
(2, 8, 'Сякэ Маки', 645, '8389da39f6d7e1fa7d3c62c873a10265be4d8c2e'),
(3, 8, 'Ясай', 750, '4442949fb6dd47c06624ee9399b2c1015a063e0c'),
(4, 8, 'Сумо', 1100, '2be5c01785d473988cc0ac6fb6cab814ff33f179'),
(5, 8, 'Калифорния с крабом', 1390, 'd4d65298ed563dd5bcfb990305b03db6a9e337a3'),
(6, 8, 'Дракон Блэк', 1780, '7a4a097fa730be30ddbc6c31a7a1a1e9829c37e6'),
(7, 8, 'Масаго кунсей', 1260, '51104ec0a6a44adaf5ff790126dfa919ee1d9480'),
(8, 8, 'Филадельфия', 1490, '2c8677e63ac5b93f97f5e2941fe8a4ac6fd93049'),
(10, 8, 'Чикен темпура', 1520, '3525f4d1c125c233b648112698f5e4cf350c9634'),
(11, 8, 'Хай тек', 1590, 'd3cebc075f608e8fccf9d8db1dd22f02ff599b66'),
(12, 8, 'Филадельфия темпура', 1560, '6fd6e8377aef8c8c47567c74058fa9cb512c3d84'),
(13, 8, 'Эби фурай', 1520, '3cebe5f95fd3de87d34004f023f651161791e6de'),
(14, 8, 'Сякэ унаги фурай', 1660, '4b9a39643c542051e1524fcc9d42c6178fc35923'),
(15, 8, 'Антигейша', 1780, 'd7f8330d8ff0ed46a8c79bff5ac18f580596dc19'),
(16, 8, 'Бомба', 1760, 'fef33f5a28d37786ea170496055cb95b97c03d5b'),
(17, 8, 'Вулкан', 1500, 'cc796e56780bab37650ae1dd99eb099a6888afff'),
(18, 8, 'Чикен', 1260, '72190698056b72c9a3f41f5415534487426352be');

-- --------------------------------------------------------

--
-- Структура таблицы `t_rolls_ingredients`
--

CREATE TABLE `t_rolls_ingredients` (
  `rolls_id` bigint(20) NOT NULL,
  `ingredients_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_rolls_ingredients`
--

INSERT INTO `t_rolls_ingredients` (`rolls_id`, `ingredients_id`) VALUES
(2, 10),
(3, 1),
(3, 3),
(4, 1),
(4, 10),
(4, 7),
(4, 20),
(5, 3),
(5, 8),
(5, 18),
(6, 1),
(6, 10),
(6, 9),
(7, 1),
(7, 3),
(7, 8),
(8, 3),
(8, 10),
(1, 1),
(10, 1),
(10, 3),
(11, 2),
(11, 1),
(11, 3),
(11, 10),
(12, 1),
(12, 10),
(13, 3),
(13, 7),
(13, 10),
(14, 1),
(14, 3),
(14, 10),
(14, 14),
(15, 1),
(15, 7),
(15, 18),
(16, 3),
(16, 1),
(16, 7),
(16, 13),
(17, 1),
(17, 3),
(17, 7),
(17, 17),
(18, 1),
(18, 3),
(18, 7),
(18, 13);

-- --------------------------------------------------------

--
-- Структура таблицы `t_sushi`
--

CREATE TABLE `t_sushi` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `sushi_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_sushi`
--

INSERT INTO `t_sushi` (`id`, `name`, `price`, `sushi_picture`) VALUES
(1, 'Сякэ', 290, '56644af048820e13f124f00ecd599acf927db5d0'),
(3, 'Унаги', 340, '4e5ba9b02167ef8c5ba8003de71bd1b2994a34a3'),
(4, 'Спайси краб', 360, '40194ecca9426f58c083b6ef2ebdd82e0888d0eb'),
(6, 'Запеченный угорь', 400, 'baeb4da81d9dfe77734153058de15298930d6631'),
(7, 'Запеченная креветка', 400, '9f84d35afea1e5f14053b9fd283505198f236a20'),
(8, 'Спайси сякэ', 420, '287961b946c8b473edef86e326cc474ef137baed'),
(9, ' Спайси мидии', 360, '7f5df54ee53f27239d23788721eb8315455ce59e'),
(10, 'Чукка', 290, '8312350355b9ddd91d08cb72de3f1754b8fa9d29'),
(12, 'Запеченный лосось', 405, '82911a60f647e992ed135db9d9d741266bfe5cff'),
(13, 'Запеченная креветка', 405, '546f092fe72cf49ddc38828663180fbe744e5e11'),
(14, 'Запеченные мидии', 405, 'b9ca2c23f5ddcb019d97b39c6d704c79f10beec2');

-- --------------------------------------------------------

--
-- Структура таблицы `t_sushi_ingredients`
--

CREATE TABLE `t_sushi_ingredients` (
  `sushi_id` bigint(20) NOT NULL,
  `ingredients_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_sushi_ingredients`
--

INSERT INTO `t_sushi_ingredients` (`sushi_id`, `ingredients_id`) VALUES
(3, 13),
(3, 14),
(4, 18),
(4, 15),
(8, 15),
(8, 18),
(9, 17),
(9, 15),
(10, 19),
(7, 2),
(7, 16),
(6, 13),
(6, 16),
(12, 3),
(12, 10),
(13, 3),
(13, 2),
(14, 17),
(14, 3),
(1, 10),
(1, 1),
(1, 2),
(1, 15);

-- --------------------------------------------------------

--
-- Структура таблицы `t_users`
--

CREATE TABLE `t_users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_users`
--

INSERT INTO `t_users` (`id`, `email`, `password`, `phone_number`, `fullname`) VALUES
(3, 'Daurbek@gmail.com', '$2a$10$N0qvVuBvrMNou2k0n4UrA.w3FSt0EamEzEfpjJAiRX32nuVx.Varq', '87876564321', 'Daurbek Sakhtarov'),
(4, 'admin@gmail.com', '$2a$10$RYSjam9tQk16q/5Wi8i3Z.F9vvaN.DoRqHVW4TQTAiN4kXwo97IyC', '888888888888', 'Admin Adminov'),
(8, 'moder@gmail.com', '$2a$10$NOOHbMFa.JVdUoEBACO5peaRUksunWKs/ULBFicDRMzd0bT4KxJsS', '87777777777', 'Moderatorov Moderator Moderatorovich'),
(10, 'Dake@mail.ru', '$2a$10$7gNcqzTuJRRRZGKO..itvueIJBc0M4EJUDfeIC6E9ypCbrRJr0CaW', '87776665511', 'Dakeev Dake Dakievich');

-- --------------------------------------------------------

--
-- Структура таблицы `t_users_roles`
--

CREATE TABLE `t_users_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `t_users_roles`
--

INSERT INTO `t_users_roles` (`users_id`, `roles_id`) VALUES
(4, 3),
(4, 2),
(4, 1),
(8, 3),
(8, 2),
(10, 3),
(3, 3);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `sets`
--
ALTER TABLE `sets`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sets_rolls_list`
--
ALTER TABLE `sets_rolls_list`
  ADD KEY `FK55m4rbatsiwdtixe3c08fty2b` (`rolls_list_id`),
  ADD KEY `FKk73qncp188ub72ucw7iewu1sl` (`sets_id`);

--
-- Индексы таблицы `sets_sushi_list`
--
ALTER TABLE `sets_sushi_list`
  ADD KEY `FKaf5xckyje1p52791ktrx9dut0` (`sushi_list_id`),
  ADD KEY `FKsqg3fxalkrwgifnbg7hieufji` (`sets_id`);

--
-- Индексы таблицы `t_drinks`
--
ALTER TABLE `t_drinks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_ingredients`
--
ALTER TABLE `t_ingredients`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_items`
--
ALTER TABLE `t_items`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_items_ingredients`
--
ALTER TABLE `t_items_ingredients`
  ADD KEY `FK84f9xarykde0ig79qbgieo29y` (`ingredients_id`),
  ADD KEY `FKi52dx8k5y5tsidixtt6sax36` (`sushi_id`),
  ADD KEY `FK4pf2nbn5gvi6m41v3oqtm694r` (`rolls_id`);

--
-- Индексы таблицы `t_items_rolls_list`
--
ALTER TABLE `t_items_rolls_list`
  ADD KEY `FKh69ork53sb2o5otoki4om2qi2` (`rolls_list_id`),
  ADD KEY `FK882jend96tosw0ctkkde39opw` (`sets_id`);

--
-- Индексы таблицы `t_items_sushi_list`
--
ALTER TABLE `t_items_sushi_list`
  ADD KEY `FK3617qrddxcfo3crfx8uqgln2j` (`sushi_list_id`),
  ADD KEY `FKisknm70m3sdx73i1sisw84o51` (`sets_id`);

--
-- Индексы таблицы `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_roles`
--
ALTER TABLE `t_roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_rolls`
--
ALTER TABLE `t_rolls`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_rolls_ingredients`
--
ALTER TABLE `t_rolls_ingredients`
  ADD KEY `FK6o76qv3o1w293o8whfnaeucbx` (`ingredients_id`),
  ADD KEY `FKnyhgrkqbnr36thxxw986drxkm` (`rolls_id`);

--
-- Индексы таблицы `t_sushi`
--
ALTER TABLE `t_sushi`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_sushi_ingredients`
--
ALTER TABLE `t_sushi_ingredients`
  ADD KEY `FKc55wmje04vg9b8x13sbhrxdtq` (`ingredients_id`),
  ADD KEY `FKs5p7ok2qknm6x8112s8jkcydi` (`sushi_id`);

--
-- Индексы таблицы `t_users`
--
ALTER TABLE `t_users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_users_roles`
--
ALTER TABLE `t_users_roles`
  ADD KEY `FK9qf4n9htwf2hlfnqoucqmyeg9` (`roles_id`),
  ADD KEY `FK8rghlpl6w68ymy8c5sdv4bkeu` (`users_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `sets`
--
ALTER TABLE `sets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `t_drinks`
--
ALTER TABLE `t_drinks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `t_ingredients`
--
ALTER TABLE `t_ingredients`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблицы `t_order`
--
ALTER TABLE `t_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `t_roles`
--
ALTER TABLE `t_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `t_rolls`
--
ALTER TABLE `t_rolls`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT для таблицы `t_sushi`
--
ALTER TABLE `t_sushi`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `t_users`
--
ALTER TABLE `t_users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `sets_rolls_list`
--
ALTER TABLE `sets_rolls_list`
  ADD CONSTRAINT `FK55m4rbatsiwdtixe3c08fty2b` FOREIGN KEY (`rolls_list_id`) REFERENCES `t_rolls` (`id`),
  ADD CONSTRAINT `FKk73qncp188ub72ucw7iewu1sl` FOREIGN KEY (`sets_id`) REFERENCES `sets` (`id`);

--
-- Ограничения внешнего ключа таблицы `sets_sushi_list`
--
ALTER TABLE `sets_sushi_list`
  ADD CONSTRAINT `FKaf5xckyje1p52791ktrx9dut0` FOREIGN KEY (`sushi_list_id`) REFERENCES `t_sushi` (`id`),
  ADD CONSTRAINT `FKsqg3fxalkrwgifnbg7hieufji` FOREIGN KEY (`sets_id`) REFERENCES `sets` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_items_ingredients`
--
ALTER TABLE `t_items_ingredients`
  ADD CONSTRAINT `FK4pf2nbn5gvi6m41v3oqtm694r` FOREIGN KEY (`rolls_id`) REFERENCES `t_items` (`id`),
  ADD CONSTRAINT `FK84f9xarykde0ig79qbgieo29y` FOREIGN KEY (`ingredients_id`) REFERENCES `t_ingredients` (`id`),
  ADD CONSTRAINT `FKi52dx8k5y5tsidixtt6sax36` FOREIGN KEY (`sushi_id`) REFERENCES `t_items` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_items_rolls_list`
--
ALTER TABLE `t_items_rolls_list`
  ADD CONSTRAINT `FK882jend96tosw0ctkkde39opw` FOREIGN KEY (`sets_id`) REFERENCES `t_items` (`id`),
  ADD CONSTRAINT `FKh69ork53sb2o5otoki4om2qi2` FOREIGN KEY (`rolls_list_id`) REFERENCES `t_items` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_items_sushi_list`
--
ALTER TABLE `t_items_sushi_list`
  ADD CONSTRAINT `FK3617qrddxcfo3crfx8uqgln2j` FOREIGN KEY (`sushi_list_id`) REFERENCES `t_items` (`id`),
  ADD CONSTRAINT `FKisknm70m3sdx73i1sisw84o51` FOREIGN KEY (`sets_id`) REFERENCES `t_items` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_rolls_ingredients`
--
ALTER TABLE `t_rolls_ingredients`
  ADD CONSTRAINT `FK6o76qv3o1w293o8whfnaeucbx` FOREIGN KEY (`ingredients_id`) REFERENCES `t_ingredients` (`id`),
  ADD CONSTRAINT `FKnyhgrkqbnr36thxxw986drxkm` FOREIGN KEY (`rolls_id`) REFERENCES `t_rolls` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_sushi_ingredients`
--
ALTER TABLE `t_sushi_ingredients`
  ADD CONSTRAINT `FKc55wmje04vg9b8x13sbhrxdtq` FOREIGN KEY (`ingredients_id`) REFERENCES `t_ingredients` (`id`),
  ADD CONSTRAINT `FKs5p7ok2qknm6x8112s8jkcydi` FOREIGN KEY (`sushi_id`) REFERENCES `t_sushi` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_users_roles`
--
ALTER TABLE `t_users_roles`
  ADD CONSTRAINT `FK8rghlpl6w68ymy8c5sdv4bkeu` FOREIGN KEY (`users_id`) REFERENCES `t_users` (`id`),
  ADD CONSTRAINT `FK9qf4n9htwf2hlfnqoucqmyeg9` FOREIGN KEY (`roles_id`) REFERENCES `t_roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

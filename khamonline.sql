-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2020 at 02:00 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `khamonline`
--

-- --------------------------------------------------------

--
-- Table structure for table `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `type` varchar(20) COLLATE utf8_vietnamese_ci NOT NULL,
  `script` varchar(1000) COLLATE utf8_vietnamese_ci NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '3.2', 'update', 'SQL', 'V3.2__update.sql', -53734780, 'root', '2020-10-12 16:53:26', 82, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mto_channel`
--

CREATE TABLE `mto_channel` (
  `id` int(11) NOT NULL,
  `key_` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `status` int(5) NOT NULL,
  `thumbnail` varchar(128) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `type_channel_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mto_channel`
--

INSERT INTO `mto_channel` (`id`, `key_`, `name`, `status`, `thumbnail`, `weight`, `type_channel_id`) VALUES
(1, 'Mắt đỏ', 'Mắt đỏ', 1, '', 3, 1),
(2, 'eye', 'Đục thủy tinh thể', 0, '', 2, 1),
(3, 't1', 'Đau vai gáy', 0, '', 1, 2),
(4, 't2', 'Cột sống', 0, '', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `mto_comment`
--

CREATE TABLE `mto_comment` (
  `id` bigint(20) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `pid` bigint(20) NOT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `mto_comment`
--

INSERT INTO `mto_comment` (`id`, `author_id`, `content`, `created`, `pid`, `post_id`, `status`) VALUES
(1, 1, 'Hen ban mot ngay som nhat!', '2020-10-17 08:27:19', 0, 14, 0),
(2, 1, 'Hen ban mot ngay som nhat!', '2020-10-18 17:33:42', 0, 29, 0);

-- --------------------------------------------------------

--
-- Table structure for table `mto_favorite`
--

CREATE TABLE `mto_favorite` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mto_links`
--

CREATE TABLE `mto_links` (
  `id` bigint(20) NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `url` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mto_message`
--

CREATE TABLE `mto_message` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `event` int(11) NOT NULL,
  `from_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `mto_message`
--

INSERT INTO `mto_message` (`id`, `created`, `event`, `from_id`, `post_id`, `status`, `user_id`) VALUES
(1, '2020-10-17 08:27:19', 3, 1, 14, 1, 2),
(2, '2020-10-18 17:33:42', 3, 1, 29, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `mto_options`
--

CREATE TABLE `mto_options` (
  `id` bigint(20) NOT NULL,
  `key_` varchar(32) DEFAULT NULL,
  `type` int(5) DEFAULT 0,
  `value` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mto_options`
--

INSERT INTO `mto_options` (`id`, `key_`, `type`, `value`) VALUES
(1, 'site_name', 0, 'Khám bệnh online'),
(2, 'site_domain', 0, 'http://mtons.com'),
(3, 'site_keywords', 0, 'khambenh,care'),
(4, 'site_description', 0, 'Nền tảng trao đổi bác sĩ và bệnh nhân'),
(5, 'site_metas', 0, ''),
(6, 'site_copyright', 0, 'Copyright © KhamOnline'),
(7, 'site_icp', 0, ''),
(8, 'qq_callback', 0, ''),
(9, 'qq_app_id', 0, ''),
(10, 'qq_app_key', 0, ''),
(11, 'weibo_callback', 0, ''),
(12, 'weibo_client_id', 0, ''),
(13, 'weibo_client_sercret', 0, ''),
(14, 'github_callback', 0, ''),
(15, 'github_client_id', 0, ''),
(16, 'github_secret_key', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `mto_post`
--

CREATE TABLE `mto_post` (
  `id` bigint(20) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `diachi` varchar(64) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `favors` int(11) NOT NULL,
  `featured` int(11) NOT NULL,
  `ngaykham` varchar(64) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  `summary` varchar(140) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tags` varchar(64) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `telephone` varchar(64) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `thumbnail` varchar(128) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `title` varchar(64) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `views` int(11) NOT NULL,
  `weight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `mto_post`
--

INSERT INTO `mto_post` (`id`, `author_id`, `channel_id`, `comments`, `created`, `diachi`, `favors`, `featured`, `ngaykham`, `status`, `summary`, `tags`, `telephone`, `thumbnail`, `title`, `views`, `weight`) VALUES
(1, 2, 2, 0, '2020-10-14 12:09:04', 'dfd', 0, 0, '2020-10-14T19:08', 0, 'fdfd', ',,,', '0346173360', '', 'abc', 1, 0),
(2, 2, 2, 0, '2020-10-14 12:13:51', 'dfd', 0, 0, '2020-10-14T19:13', 0, 'sdsds', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(3, 2, 2, 0, '2020-10-14 12:44:19', 'dfd', 0, 0, '2020-10-15T19:42', 2, 'sdfsdf', ',,,', '0346173360', '', 'Khám nội soi xx', 0, 0),
(4, 2, 2, 0, '2020-10-14 22:51:52', 'dfd', 0, 0, '2020-10-14T05:51', 0, 'sđs', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(5, 2, 3, 0, '2020-10-14 23:15:23', 'ha noi', 0, 0, '2020-10-15T08:17', 0, 'dsfsdf', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(6, 2, 4, 0, '2020-10-14 23:24:40', 'ha noi', 0, 0, '2020-10-15T08:24', 0, 'df', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(7, 2, 3, 0, '2020-10-14 23:49:32', 'ha noi', 0, 0, '2017-10-14T11:48', 0, 'dfdf', ',,,', '01206221590', '', 'kham tong quat', 0, 0),
(8, 2, 3, 0, '2020-10-14 23:58:01', 'ha noi', 0, 0, '2020-10-08T06:59', 0, 'dfdsd', ',,,', '0346173360', '', 'Khám nội soi 12', 0, 0),
(9, 2, 3, 0, '2020-10-15 00:46:20', 'ha noi', 0, 0, '2020-10-14T10:46', 0, 'đsfdsdf', ',,,', '0346173360', '', 'Khám nội soi 134', 0, 0),
(10, 2, 3, 0, '2020-10-16 13:03:05', 'ha noi', 0, 0, '2020-10-16T20:04', 0, 'vxcvxc', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(11, 2, 4, 0, '2020-10-16 13:12:25', 'ha noi', 0, 0, '2020-10-17T23:11', 0, 'abc', ',,,', '0346173360', '', 'Khám nội soi 123', 0, 0),
(12, 2, 3, 0, '2020-10-16 13:25:09', 'ha noi', 0, 0, '2020-10-16T20:27', 0, 'dsfsdf', ',,,', '0346173360', '', 'test payman paypal', 0, 0),
(13, 2, 3, 0, '2020-10-16 13:30:59', 'ha noi', 0, 0, '2020-10-16T20:33', 0, 'xvcvx', ',,,', '0346173360', '', 'test payman paypal', 0, 0),
(14, 2, 2, 1, '2020-10-16 15:46:43', 'ha noi', 0, 1, '2020-10-16T13:49', 2, 'rỷeyeryeyeye', ',,,', 'auylyul', '', 'Khám nội soi xx', 1, 0),
(15, 2, 3, 0, '2020-10-16 16:00:49', 'ha noi', 0, 0, '2020-10-17T23:03', 2, 'sdf', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(17, 2, 3, 0, '2020-10-18 13:45:14', 'ha noi', 0, 0, '2020-10-14T20:44', 4, 'dfdsf', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(18, 2, 3, 0, '2020-10-18 14:25:10', 'ha noi', 0, 0, '2020-10-30T21:24', 4, 'sdf', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(19, 2, 3, 0, '2020-10-18 14:31:02', 'ha noi', 0, 0, '2020-10-30T21:32', 4, 'dsfdsf', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(20, 2, 4, 0, '2020-10-18 14:57:02', 'ha noi', 0, 0, '2020-10-29T21:56', 4, 'gsdgs', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(21, 2, 3, 0, '2020-10-18 15:00:54', 'ha noi', 0, 0, '2020-10-30T22:03', 4, 'dfazxc', ',,,', '0346173360', '', 'Khám nội soi dsd', 0, 0),
(22, 2, 2, 0, '2020-10-18 15:13:15', 'ha noi', 0, 0, '2020-10-28T22:13', 4, 'aSFcsfcas', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(23, 2, 2, 0, '2020-10-18 15:20:46', 'Xv', 0, 0, '2020-10-24T22:20', 4, 'szvxZVV', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(24, 2, 3, 0, '2020-10-18 15:58:55', 'ha noi', 0, 0, '2020-10-29T22:58', 4, 'dssdf', ',,,', '0346173360', '', 'Khám nội soi xx', 0, 0),
(25, 2, 3, 0, '2020-10-18 16:15:14', 'ha noi', 0, 0, '2020-10-15T23:18', 4, 'ddfdsf', ',,,', '0346173360', '', 'Khám nội soi', 0, 0),
(26, 2, 3, 0, '2020-10-18 16:16:38', 'ha noi', 0, 0, '2020-10-30T23:20', 4, 'sdfds', ',,,', '0346173360', '', 'Khám nội soi dfdf', 0, 0),
(27, 2, 2, 0, '2020-10-18 16:33:56', 'ha noi', 0, 0, '2020-10-30T23:36', 4, 'xczx', ',,,', '0346173360', '', 'Khám nội soi sdf', 0, 0),
(28, 2, 4, 0, '2020-10-18 16:42:45', 'ha noi', 0, 0, '2020-10-30T23:45', 4, 'dfaf', ',,,', '0346173360', '', 'Khám nội soi xx', 0, 0),
(29, 2, 3, 1, '2020-10-18 16:50:40', 'ha noi', 0, 1, '2020-10-30T14:50', 0, 'fsdfs', ',,,', '0346173360', '', 'Khám nội soi', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `mto_post_attribute`
--

CREATE TABLE `mto_post_attribute` (
  `id` bigint(20) NOT NULL,
  `content` longtext COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `editor` varchar(16) COLLATE utf8_vietnamese_ci DEFAULT 'tinymce'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `mto_post_attribute`
--

INSERT INTO `mto_post_attribute` (`id`, `content`, `editor`) VALUES
(1, 'fdfd', 'markdown'),
(2, 'sdsds', 'markdown'),
(3, 'sdfsdf', 'markdown'),
(4, 'sđs', 'markdown'),
(5, 'dsfsdf', 'markdown'),
(6, 'df', 'markdown'),
(7, 'dfdf', 'markdown'),
(8, 'dfdsd', 'markdown'),
(9, 'đsfdsdf', 'markdown'),
(10, 'vxcvxc', 'markdown'),
(11, 'abc', 'markdown'),
(12, 'dsfsdf', 'markdown'),
(13, 'xvcvx', 'markdown'),
(14, 'rỷeyeryeyeye', 'markdown'),
(15, 'sdf', 'markdown'),
(17, 'dfdsf', 'markdown'),
(18, 'sdf', 'markdown'),
(19, 'dsfdsf', 'markdown'),
(20, 'gsdgs', 'markdown'),
(21, 'dfazxc', 'markdown'),
(22, 'aSFcsfcas', 'markdown'),
(23, 'szvxZVV', 'markdown'),
(24, 'dssdf', 'markdown'),
(25, 'ddfdsf', 'markdown'),
(26, 'sdfds', 'markdown'),
(27, 'xczx', 'markdown'),
(28, 'dfaf', 'markdown'),
(29, 'fsdfs', 'markdown');

-- --------------------------------------------------------

--
-- Table structure for table `mto_post_resource`
--

CREATE TABLE `mto_post_resource` (
  `id` bigint(20) NOT NULL,
  `path` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) NOT NULL,
  `sort` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mto_post_tag`
--

CREATE TABLE `mto_post_tag` (
  `id` bigint(20) NOT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  `weight` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mto_resource`
--

CREATE TABLE `mto_resource` (
  `id` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT NULL,
  `md5` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL DEFAULT '',
  `path` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL DEFAULT '',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `mto_resource`
--

INSERT INTO `mto_resource` (`id`, `amount`, `create_time`, `md5`, `path`, `update_time`) VALUES
(1, 0, NULL, '2CHKIRLIGC80CO1BQ2A5BC2197', '/storage/thumbnails/_signature/2CHKIRLIGC80CO1BQ2A5BC2197.jpg', '2020-10-12 23:56:05');

-- --------------------------------------------------------

--
-- Table structure for table `mto_security_code`
--

CREATE TABLE `mto_security_code` (
  `id` bigint(20) NOT NULL,
  `code` varchar(16) COLLATE utf8_vietnamese_ci NOT NULL,
  `created` datetime NOT NULL,
  `expired` datetime NOT NULL,
  `key_` varchar(64) COLLATE utf8_vietnamese_ci NOT NULL,
  `status` int(11) DEFAULT NULL,
  `target` varchar(64) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mto_tag`
--

CREATE TABLE `mto_tag` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `latest_post_id` bigint(20) NOT NULL,
  `name` varchar(32) COLLATE utf8_vietnamese_ci NOT NULL,
  `posts` int(11) NOT NULL,
  `thumbnail` varchar(128) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mto_user`
--

CREATE TABLE `mto_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `avatar` varchar(128) DEFAULT '/dist/images/ava/default.png',
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `status` int(5) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `gender` int(5) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `posts` int(11) NOT NULL,
  `signature` varchar(140) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mto_user`
--

INSERT INTO `mto_user` (`id`, `username`, `name`, `avatar`, `email`, `password`, `status`, `created`, `updated`, `last_login`, `gender`, `role_id`, `comments`, `posts`, `signature`) VALUES
(1, 'admin', 'Dr. Long', 'https://en.gravatar.com/userimage/154673030/b9a54b5b990a61cc074668b2e2a0b8c0.png', 'admin@khamonline', '3TGCQF25BLHU9R7IQUITN0FCC5', 0, '2017-08-06 17:52:41', '2017-07-26 11:08:36', '2020-10-18 18:24:21', 0, 1, 2, 0, ''),
(2, 'ntphuong', 'ntphuong', 'https://en.gravatar.com/userimage/154673030/b9a54b5b990a61cc074668b2e2a0b8c0.png', NULL, '15QLDD50TA805F8P67DLOJO1TD', 0, '2020-10-14 12:08:28', NULL, '2020-10-18 16:52:42', 0, NULL, 0, 28, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mto_user_oauth`
--

CREATE TABLE `mto_user_oauth` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `access_token` varchar(128) DEFAULT NULL,
  `expire_in` varchar(128) DEFAULT NULL,
  `oauth_code` varchar(128) DEFAULT NULL,
  `oauth_type` int(11) DEFAULT NULL,
  `oauth_user_id` varchar(128) DEFAULT NULL,
  `refresh_token` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `payment_history`
--

CREATE TABLE `payment_history` (
  `id` int(11) NOT NULL,
  `mto_post_id` bigint(20) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `amount` float NOT NULL,
  `recept_id` bigint(20) NOT NULL,
  `invoice_number` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `currency` varchar(128) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `payment_history`
--

INSERT INTO `payment_history` (`id`, `mto_post_id`, `status`, `amount`, `recept_id`, `invoice_number`, `created_at`, `updated_at`, `currency`) VALUES
(2, 29, 0, 10, 0, 'PAYID-L6GHEXQ5A886211VF111503X', '2020-10-18 16:52:42', '2020-10-18 16:52:42', 'USD');

-- --------------------------------------------------------

--
-- Table structure for table `shiro_permission`
--

CREATE TABLE `shiro_permission` (
  `id` bigint(20) NOT NULL,
  `description` varchar(140) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `parent_id` bigint(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shiro_permission`
--

INSERT INTO `shiro_permission` (`id`, `description`, `name`, `parent_id`, `version`, `weight`) VALUES
(1, 'Thông số', 'admin', 0, 0, 0),
(2, 'Quản lý cột', 'channel:list', 0, 0, 0),
(3, 'Chỉnh sửa phần', 'channel:update', 2, 0, 0),
(4, 'Xóa phần', 'channel:delete', 2, 0, 0),
(5, 'Quản lý bài viết', 'post:list', 0, 0, 0),
(6, 'Chỉnh sửa bài viết', 'post:update', 5, 0, 0),
(7, 'Xóa bài viết', 'post:delete', 5, 0, 0),
(8, 'Quản lý bình luận', 'comment:list', 0, 0, 0),
(9, 'Xóa nhận xét', 'comment:delete', 8, 0, 0),
(10, 'Quản lý người dùng', 'user:list', 0, 0, 0),
(11, 'Ủy quyền người dùng', 'user:role', 10, 0, 0),
(12, 'đổi mật khẩu', 'user:pwd', 10, 0, 0),
(13, 'Kích hoạt người dùng', 'user:open', 10, 0, 0),
(14, 'Khoá người dùng', 'user:close', 10, 0, 0),
(15, 'Quản lý vai trò', 'role:list', 0, 0, 0),
(16, 'Sửa đổi vai trò', 'role:update', 15, 0, 0),
(17, 'Xóa vai trò', 'role:delete', 15, 0, 0),
(18, 'Quản lý chủ đề', 'theme:index', 0, 0, 0),
(19, 'Cấu hình hệ thông', 'options:index', 0, 0, 0),
(20, 'Thay đổi cài đặt', 'options:update', 19, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `shiro_role`
--

CREATE TABLE `shiro_role` (
  `id` bigint(20) NOT NULL,
  `description` varchar(140) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shiro_role`
--

INSERT INTO `shiro_role` (`id`, `description`, `name`, `status`) VALUES
(1, NULL, 'admin', 0);

-- --------------------------------------------------------

--
-- Table structure for table `shiro_role_permission`
--

CREATE TABLE `shiro_role_permission` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shiro_role_permission`
--

INSERT INTO `shiro_role_permission` (`id`, `permission_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1),
(13, 13, 1),
(14, 14, 1),
(15, 15, 1),
(16, 16, 1),
(17, 17, 1),
(18, 18, 1),
(19, 19, 1),
(20, 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `shiro_user_role`
--

CREATE TABLE `shiro_user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shiro_user_role`
--

INSERT INTO `shiro_user_role` (`id`, `role_id`, `user_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `type_channel`
--

CREATE TABLE `type_channel` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `type_channel`
--

INSERT INTO `type_channel` (`id`, `name`) VALUES
(1, 'Nhóm bệnh mắt'),
(2, 'Nhóm bênh xương khớp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Indexes for table `mto_channel`
--
ALTER TABLE `mto_channel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_channel_id` (`type_channel_id`);

--
-- Indexes for table `mto_comment`
--
ALTER TABLE `mto_comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IK_POST_ID` (`post_id`);

--
-- Indexes for table `mto_favorite`
--
ALTER TABLE `mto_favorite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IK_USER_ID` (`user_id`);

--
-- Indexes for table `mto_links`
--
ALTER TABLE `mto_links`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mto_message`
--
ALTER TABLE `mto_message`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mto_options`
--
ALTER TABLE `mto_options`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mto_post`
--
ALTER TABLE `mto_post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IK_CHANNEL_ID` (`channel_id`);

--
-- Indexes for table `mto_post_attribute`
--
ALTER TABLE `mto_post_attribute`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mto_post_resource`
--
ALTER TABLE `mto_post_resource`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IK_POST_ID` (`post_id`);

--
-- Indexes for table `mto_post_tag`
--
ALTER TABLE `mto_post_tag`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IK_TAG_ID` (`tag_id`);

--
-- Indexes for table `mto_resource`
--
ALTER TABLE `mto_resource`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_MD5` (`md5`);

--
-- Indexes for table `mto_security_code`
--
ALTER TABLE `mto_security_code`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_shxjkbkgnpxa80pnv4ts57o19` (`key_`);

--
-- Indexes for table `mto_tag`
--
ALTER TABLE `mto_tag`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9ki38gg59bw5agwxsc4xtednf` (`name`);

--
-- Indexes for table `mto_user`
--
ALTER TABLE `mto_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_USERNAME` (`username`);

--
-- Indexes for table `mto_user_oauth`
--
ALTER TABLE `mto_user_oauth`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mto_post_id` (`mto_post_id`);

--
-- Indexes for table `shiro_permission`
--
ALTER TABLE `shiro_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_NAME` (`name`);

--
-- Indexes for table `shiro_role`
--
ALTER TABLE `shiro_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shiro_role_permission`
--
ALTER TABLE `shiro_role_permission`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shiro_user_role`
--
ALTER TABLE `shiro_user_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_channel`
--
ALTER TABLE `type_channel`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mto_channel`
--
ALTER TABLE `mto_channel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `mto_comment`
--
ALTER TABLE `mto_comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mto_favorite`
--
ALTER TABLE `mto_favorite`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mto_links`
--
ALTER TABLE `mto_links`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mto_message`
--
ALTER TABLE `mto_message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mto_options`
--
ALTER TABLE `mto_options`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `mto_post`
--
ALTER TABLE `mto_post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `mto_post_resource`
--
ALTER TABLE `mto_post_resource`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mto_post_tag`
--
ALTER TABLE `mto_post_tag`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mto_resource`
--
ALTER TABLE `mto_resource`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `mto_security_code`
--
ALTER TABLE `mto_security_code`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mto_tag`
--
ALTER TABLE `mto_tag`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mto_user`
--
ALTER TABLE `mto_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mto_user_oauth`
--
ALTER TABLE `mto_user_oauth`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment_history`
--
ALTER TABLE `payment_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shiro_permission`
--
ALTER TABLE `shiro_permission`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `shiro_role`
--
ALTER TABLE `shiro_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shiro_role_permission`
--
ALTER TABLE `shiro_role_permission`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `shiro_user_role`
--
ALTER TABLE `shiro_user_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `type_channel`
--
ALTER TABLE `type_channel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mto_channel`
--
ALTER TABLE `mto_channel`
  ADD CONSTRAINT `mto_channel_ibfk_1` FOREIGN KEY (`type_channel_id`) REFERENCES `type_channel` (`id`);

--
-- Constraints for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD CONSTRAINT `payment_history_ibfk_1` FOREIGN KEY (`mto_post_id`) REFERENCES `mto_post` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

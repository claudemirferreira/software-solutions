<?php
/**
 * The base configurations of the WordPress.
 *
 * This file has the following configurations: MySQL settings, Table Prefix,
 * Secret Keys, WordPress Language, and ABSPATH. You can find more information
 * by visiting {@link http://codex.wordpress.org/Editing_wp-config.php Editing
 * wp-config.php} Codex page. You can get the MySQL settings from your web host.
 *
 * This file is used by the wp-config.php creation script during the
 * installation. You don't have to use the web site, you can just copy this file
 * to "wp-config.php" and fill in the values.
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'centrala_wp1');

/** MySQL database username */
define('DB_USER', 'centrala_wp1');

/** MySQL database password */
define('DB_PASSWORD', 'BZcsXLU8i0');

/** MySQL hostname */
define('DB_HOST', 'localhost');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8');

/** The Database Collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         '2y1ydrlXvyVQrsGte34g5VW_4UFCJSvIWyjDBaEphw_lKriaGXpzdQgrjYODdGjt');
define('SECURE_AUTH_KEY',  'PeTmc7EHdBRbMObY1Ru5P1TL_tMPVFc8PtdtB81jKl9HV1CBX0CkmLnCZq0zi8YW');
define('LOGGED_IN_KEY',    'rs0_BgW4Dd887b8Wt5RS1yef2lJsGxHUyQDBHXwe5_gqdHBeXltH2yVWCfX10Lqo');
define('NONCE_KEY',        'D4VZfyvTz6BCDVId5cTYbBOUXpBwnrqpGnImsknM0_YVQJhc2DrpQkU5YMNF52xE');
define('AUTH_SALT',        '7R9b0MGODrg9BJzn1MY3gzhBsdAhvmXruKFqQS1gxAGqDoXPBzznyaq2nFC3TpUJ');
define('SECURE_AUTH_SALT', 'ivEwUTw6zmG7WqA2AzCLFN9grk6I0El1tGPCJYFcHA2Iq7CEkgsW4XtByhz3uwGe');
define('LOGGED_IN_SALT',   '9ZABzKOPpLWxx0xlrgI22korkMAGBjG5eOhLujZflBUzzuDno1Mke2_oap0HhCsH');
define('NONCE_SALT',       'Lj9fiXQ6vitrp7JZ58OI0otOssDpb1XFr2rQYszX6GM2XO6hhx6GqkPurzvxTFI1');

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each a unique
 * prefix. Only numbers, letters, and underscores please!
 */
$table_prefix  = 'wp_';

/**
 * WordPress Localized Language, defaults to English.
 *
 * Change this to localize WordPress. A corresponding MO file for the chosen
 * language must be installed to wp-content/languages. For example, install
 * de_DE.mo to wp-content/languages and set WPLANG to 'de_DE' to enable German
 * language support.
 */
define('WPLANG', '');

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 */
define('WP_DEBUG', false);

/* That's all, stop editing! Happy blogging. */

/** Absolute path to the WordPress directory. */
if ( !defined('ABSPATH') )
	define('ABSPATH', dirname(__FILE__) . '/');

/** Sets up WordPress vars and included files. */
require_once(ABSPATH . 'wp-settings.php');

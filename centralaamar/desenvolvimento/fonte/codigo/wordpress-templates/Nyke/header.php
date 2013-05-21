<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" <?php language_attributes(); ?>>
<head profile="http://gmpg.org/xfn/11">
<meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />
<title><?php wp_title(''); ?><?php if(wp_title('', false)) { echo ' :'; } ?> <?php bloginfo('name'); ?></title>

<link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="<?php bloginfo('template_directory'); ?>/nivo-slider.css" media="screen" />

<link href='http://fonts.googleapis.com/css?family=Open+Sans:regular,bold' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Mako' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css' />

<link rel="alternate" type="application/rss+xml" title="<?php bloginfo('name'); ?> RSS Feed" href="<?php bloginfo('rss2_url'); ?>" />
<link rel="alternate" type="application/atom+xml" title="<?php bloginfo('name'); ?> Atom Feed" href="<?php bloginfo('atom_url'); ?>" />
<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />

<?php 
wp_enqueue_script('jquery');
wp_enqueue_script('nivo', get_stylesheet_directory_uri() . '/js/jquery.nivo.slider.pack.js');
wp_enqueue_script('superfish', get_stylesheet_directory_uri() . '/js/superfish.js');
wp_enqueue_script('effects', get_stylesheet_directory_uri() . '/js/effects.js');
?>

<?php wp_get_archives('type=monthly&format=link'); ?>
<?php //comments_popup_script(); // off by default ?>
<?php 
if ( is_singular() ) wp_enqueue_script( 'comment-reply' );
wp_head(); 
?>

</head>
<body>
<div class="outer">

<div class="masthead">
	<div id="foxmenucontainer">
		<?php wp_nav_menu( array( 'container_id' => 'menu', 'theme_location' => 'primary','menu_class'=>'sfmenu','fallback_cb'=> 'fallbackmenu1' ) ); ?>	
	</div>
	
	<div id="top"> 
		<div class="head">
			<div class="blogname">
				<h1><a href="<?php bloginfo('siteurl');?>/" title="<?php bloginfo('name');?>"> <?php bloginfo('name');?></a></h1>
				<h2><?php bloginfo('description'); ?></h2>
			</div>
			<?php include (TEMPLATEPATH . '/topad.php'); ?>
		</div>
	</div>
</div>

<div id="catmenucontainer">
	<?php wp_nav_menu( array( 'container_id' => 'submenu', 'theme_location' => 'secondary','menu_class'=>'sfmenu','fallback_cb'=> 'fallbackmenu2' ) ); ?>	
	
</div>

<div id="wrapper">
<div id="casing">		
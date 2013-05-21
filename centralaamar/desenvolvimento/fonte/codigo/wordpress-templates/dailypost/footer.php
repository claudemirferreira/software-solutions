<?php
/**
 * The footer template
 *
 * @package wplook
 * @subpackage DailyPost
 * @since DailyPost 1.0
 */
?>
<?php global $options;
foreach ($options as $value) {
	if (isset($value['id']) && get_option( $value['id'] ) === FALSE && isset($value['std'])) {
		$$value['id'] = $value['std'];
	}
	elseif (isset($value['id'])) { $$value['id'] = get_option( $value['id'] ); }
}?>
</div><!--/secondary-->
<div id="social-icons">
	<div id="social-icons-margins">
		<?php if ($wpl_twitter != '') { ?>
		<a href="<?php echo $wpl_twitter; ?>" target="_blank"><img src="<?php echo get_template_directory_uri(); ?>/images/icons/twitter.png" width="22" height="22" alt="<?php echo $wpl_twitter; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_facebook != '') { ?>
		<a href="<?php echo $wpl_facebook; ?>" target="_blank"><img src="<?php echo get_template_directory_uri(); ?>/images/icons/facebook.png" width="22" height="22" alt="<?php echo $wpl_facebook; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_linkedin != '') { ?>
		<a href="<?php echo $wpl_linkedin; ?>" target="_blank"><img src="<?php echo get_template_directory_uri(); ?>/images/icons/linkedin.png" width="22" height="22" alt="<?php echo $wpl_linkedin; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_tumblr != '') {	?>
		<a href="<?php echo $wpl_tumblr; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/tumblr.png" width="22" height="22" alt="<?php echo $wpl_tumblr; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_delicious != '') {	?>
		<a href="<?php echo $wpl_delicious; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/delicious.png" width="22" height="22" alt="<?php echo $wpl_delicious; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_digg != '') {	?>
		<a href="<?php echo $wpl_digg; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/digg.png" width="22" height="22" alt="<?php echo $wpl_digg; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_stumbleupon != '') {	?>
		<a href="<?php echo $wpl_stumbleupon; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/stumbleupon.png" width="22" height="22" alt="<?php echo $wpl_stumbleupon; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_flickr != '') {	?>
		<a href="<?php echo $wpl_flickr; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/flickr.png" width="22" height="22" alt="<?php echo $wpl_flickr; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_picasa != '') {	?>
		<a href="<?php echo $wpl_picasa; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/picasa.png" width="22" height="22" alt="<?php echo $wpl_picasa; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_youtube != '') {	?>
		<a href="<?php echo $wpl_youtube; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/youtube.png" width="22" height="22" alt="<?php echo $wpl_youtube; ?>" /></a>
		<?php } ?>
		<?php if ($wpl_dribbble != '') {	?>
		<a href="<?php echo $wpl_dribbble; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/dribbble.png" width="22" height="22" alt="<?php echo $wpl_dribbble; ?>" /></a>
		<?php } ?>
        
        <a href="http://www.w3.org/html/logo/" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/html5.png" width="22" height="32" alt="HTML5" /></a>
        <a href="http://www.wplook.com" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/wplook.png" width="22" height="22" alt="Design by WPlook" /></a>
		<?php if ($wpl_rss != '') { ?>
		<a href="<?php echo $wpl_rss; ?>" target="_blank"><img src="<?php echo get_template_directory_uri() ?>/images/icons/rss.png" width="22" height="22" alt="<?php echo $wpl_rss; ?>" /></a>
		<?php } ?>
	</div><!--/social-icons-margins-->
</div><!--/social-icons-->
<div class="clear"></div>


</div>
<?php wp_footer(); ?>
</body></html>
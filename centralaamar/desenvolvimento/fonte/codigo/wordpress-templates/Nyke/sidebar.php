<div class="right">

<!-- Search form-->
<?php include (TEMPLATEPATH . '/searchform.php'); ?>	
<!-- Search form end-->

<!-- Popular content-->
<?php include (TEMPLATEPATH . '/tabs.php'); ?>	
<!-- Popular content-->

<!-- Ad Banners-->
<?php include (TEMPLATEPATH . '/sponsors.php'); ?>	
<!-- Ad Banners end-->

<!-- Wordpress widgets-->
<div class="sidebar">
<ul>
	<?php if ( function_exists('dynamic_sidebar') && dynamic_sidebar('Sidebar') ) : else : ?>
	<?php endif; ?>
</ul>
</div>
<!-- Wordpress widgets ends-->
</div>
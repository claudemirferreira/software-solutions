<div class="slide">
<div class="dframe"></div>
<div id="slider" class="nivoSlider">
		
	<?php 
		$slide_cat = get_option('nyke_gldcat'); 
		$slide_count = get_option('nyke_gldct');
		$my_query = new WP_Query('category_name='.$slide_cat.'&showposts='.$slide_count.'');
		while ($my_query->have_posts()) : $my_query->the_post();$do_not_duplicate = $post->ID;
	?>

		<img class="nivim" src="<?php bloginfo('stylesheet_directory'); ?>/timthumb.php?src=<?php echo get_image_url()?>&amp;h=300&amp;w=960&amp;zc=1"  alt="" title="<?php the_title(); ?>"/>

	<?php endwhile; ?>
		
</div>
</div>
<div class="clear"></div>
<div id="newtabs" class="tabox ">
<h3 class=" sidetitl"><?php _e('Popular'); ?></h3>
            
<div id="popular" class="tabsdiv">
<?php 
	$my_query = new WP_Query('orderby=comment_count&showposts=5');
	while ($my_query->have_posts()) : $my_query->the_post();$do_not_duplicate = $post->ID;
?>
<div class="fblock">

<?php
if ( has_post_thumbnail() ) { ?>
	<img class="thumbim" src="<?php bloginfo('stylesheet_directory'); ?>/timthumb.php?src=<?php get_image_url(); ?>&amp;h=70&amp;w=90&amp;zc=1" alt=""/>
<?php } else { ?>
	<img class="thumbim" src="<?php bloginfo('template_directory'); ?>/images/dummy.png" alt="" />
<?php } ?>

<h3><a href="<?php the_permalink() ?>" title="<?php the_title(); ?>"><?php echo substr($post->post_title,0,20); ?></a></h3>
<p>  <?php the_content_rss('', TRUE, '', 10); ?> </p>
<div class="clear"></div>
</div>
<?php endwhile; ?>
</div>
			
	
</div>
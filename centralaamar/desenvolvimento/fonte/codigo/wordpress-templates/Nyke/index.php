<?php get_header(); ?>

<?php 
if(get_option('nyke_nivo') == "Yes") { 
include (TEMPLATEPATH . '/slider.php'); 
}
?>	

<div id="content">

<?php if (have_posts()) : ?>
<?php while (have_posts()) : the_post(); ?>

<div class="post" id="post-<?php the_ID(); ?>">

<div class="title">
	<h2><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h2>
		<div class="postmeta2">
	<div class="pmet">Posted by <?php the_author(); ?>  </div>
	<div class="pmet"><?php the_time('M - j - Y'); ?></div>
	</div>	
<div class="clear"></div>
</div>

<div class="imgentry">
<div class="cdate">

<?php comments_popup_link('0 Comment', '1 Comment', '% Comments'); ?>
</div>


<?php
if ( has_post_thumbnail() ) { ?>
	<a href="<?php the_permalink() ?>"><img class="postimg" src="<?php bloginfo('stylesheet_directory'); ?>/timthumb.php?src=<?php get_image_url(); ?>&amp;h=200&amp;w=200&amp;zc=1" alt=""/></a>
<?php } else { ?>
	<a href="<?php the_permalink() ?>"><img class="postimg" src="<?php bloginfo('template_directory'); ?>/images/dummy.png" alt="" /></a>
<?php } ?>

</div>


<div class="cover">	

<div class="entry">

<?php wpe_excerpt('wpe_excerptlength_index', 'wpe_excerptmore'); ?>
<div class="clear"></div>
</div>

</div>

</div>
<div class="stripe"></div>

<?php endwhile; ?>

<div class="clear"></div>
<?php getpagenavi(); ?>
<?php else : ?>
		<h1 class="title">Not Found</h1>
		<p>Sorry, but you are looking for something that isn't here.</p>
<?php endif; ?>
<div class="clear"></div>
</div>

<?php get_sidebar(); ?>
<?php get_footer(); ?>
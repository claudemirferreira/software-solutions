<?php query_posts( '' ); ?>
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
<div class="item-blog">      
<?php
if ( has_post_thumbnail()) {
?>
<a href="<?php the_permalink(); ?>" title="<?php printf( esc_attr__( 'Permalink to %s', 'twentyten' ), the_title_attribute( 'echo=0' ) ); ?>" rel="bookmark">    <?php echo get_the_post_thumbnail( $post->ID, 'main-thumbnail' ); ?></a>
 <?
} else {
?>
<a href="<?php the_permalink(); ?>" title="<?php printf( esc_attr__( 'Permalink to %s', 'twentyten' ), the_title_attribute( 'echo=0' ) ); ?>" rel="bookmark">   
<img src="<?php bloginfo('template_url'); ?>/images/defaultthumb.jpg"; />
</a>
<? } ?>
<a href="<?php the_permalink(); ?>" title="<?php printf( esc_attr__( 'Permalink to %s', 'twentyten' ), the_title_attribute( 'echo=0' ) ); ?>" rel="bookmark">
<div class="caption-blog">
		<h2 class="entry-title">            <a href="<?php the_permalink(); ?>" title="<?php printf( esc_attr__( 'Permalink to %s', 'twentyten' ), the_title_attribute( 'echo=0' ) ); ?>" rel="bookmark"><?php the_title(); ?></a></h2>
		<div class="entry-meta"><?php the_time('F jS, Y') ?></div>
        <span>
        <?php comments_popup_link( __( 'Leave a comment', 'twentyten' ), __( '1 Comment', 'twentyten' ), __( '% Comments', 'twentyten' ) ); ?><br />
        Posted In: <?php the_category(', ') ?>
        </span>
           </a>
	</div>
 </div>
 
                <?php endwhile; ?>
                   <div class="clr"></div>
				<div id="nav-below" class="navigation">
              										<?php
include('wp-pagenavi.php');
if(function_exists('wp_pagenavi')) { wp_pagenavi(); }
?>
				</div><!-- #nav-below -->
        <?php else : ?>
                <h2 class="center">Not Found</h2>
                <p class="center">Sorry, but you are looking for something that isn't here.</p>
        <?php endif; ?>
        <?php wp_reset_query(); ?>
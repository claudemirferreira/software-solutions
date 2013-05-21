<?php
/**
 * The loop that displays a single post.
 *
 * The loop displays the posts and the post content.  See
 * http://codex.wordpress.org/The_Loop to understand it and
 * http://codex.wordpress.org/Template_Tags to understand
 * the tags used in it.
 *
 * This can be overridden in child themes with loop-single.php.
 *
 * @package WordPress
 * @subpackage Twenty_Ten
 * @since Twenty Ten 1.2
 */
?>

<?php if ( have_posts() ) while ( have_posts() ) : the_post(); ?>

				<div id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
                
                <div class="bigimage">         
<?php
if ( has_post_thumbnail()) {
?>
 <?php echo get_the_post_thumbnail( $post->ID, 'big-thumbnail' ); ?>
 <?
} else {
?>
<img src="<?php bloginfo('template_url'); ?>/images/defaultthumb.jpg"; />
<? } ?>
<span>
<h4>Information</h4>
<ul>
<li>Date:<br />
<?php the_time('F Y') ?>
</li>
<li>Equipment Used:<br />
<?php echo get_the_term_list($post->ID, 'equipment', '', ', ', ''); ?>
</li>
<li>Location:<br />
<?php echo get_the_term_list($post->ID, 'location', '', ', ', ''); ?>
</li>
<li>Category:<br />
<?php the_category(', ') ?>
</li>
<li>Tags:<br />
<p><?php the_tags('', ', ', ''); ?> </p>
</li>
</ul>
</span>
 </div>
                
                
					<h1 class="entry-title"><?php the_title(); ?></h1>
					<div class="entry-content">
						<?php the_content(); ?>
						<?php wp_link_pages( array( 'before' => '<div class="page-link">' . __( 'Pages:', 'twentyten' ), 'after' => '</div>' ) ); ?>
					</div><!-- .entry-content -->
<?php if ( get_the_author_meta( 'description' ) ) : // If a user has filled out their description, show a bio on their entries  ?>
					<div id="entry-author-info">
						<div id="author-avatar">
							<?php echo get_avatar( get_the_author_meta( 'user_email' ), apply_filters( 'twentyten_author_bio_avatar_size', 60 ) ); ?>
						</div><!-- #author-avatar -->
						<div id="author-description">
							<h2><?php printf( esc_attr__( 'About %s', 'twentyten' ), get_the_author() ); ?></h2>
							<?php the_author_meta( 'description' ); ?>
							<div id="author-link">
								<a href="<?php echo get_author_posts_url( get_the_author_meta( 'ID' ) ); ?>" rel="author">
									<?php printf( __( 'View all posts by %s <span class="meta-nav">&rarr;</span>', 'twentyten' ), get_the_author() ); ?>
								</a>
							</div><!-- #author-link	-->
						</div><!-- #author-description -->
					</div><!-- #entry-author-info -->
                    					<div class="entry-meta">
						<?php twentyten_posted_on(); ?>
					</div><!-- .entry-meta -->
<?php endif; ?>
				</div><!-- #post-## -->
                <div class="clr"></div>
<div class="morephotos">
<?php
//for use in the loop, list 5 post titles related to first tag on current post
$tags = wp_get_post_tags($post->ID);
if ($tags) {
  echo '<h4>More Photos</h4>';
  $first_tag = $tags[0]->term_id;
  $args=array(
    'showposts'=>3,
    'caller_get_posts'=>3,
	'post_type'=>'photo'
   );
  $my_query = new WP_Query($args);
  if( $my_query->have_posts() ) {
    while ($my_query->have_posts()) : $my_query->the_post(); ?>
      <div class="item" style="margin:0px 20px 0px 0px;">         
<?php
if ( has_post_thumbnail()) {
?>
 <?php echo get_the_post_thumbnail( $post->ID, 'main-thumbnail' ); ?>
 <?
} else {
?>
<img src="<?php bloginfo('template_url'); ?>/images/defaultthumb.jpg"; />
<? } ?>
<a href="<?php the_permalink(); ?>" title="<?php printf( esc_attr__( 'Permalink to %s', 'twentyten' ), the_title_attribute( 'echo=0' ) ); ?>" rel="bookmark">
	<div class="caption">
		<h2 class="entry-title">            <a href="<?php the_permalink(); ?>" title="<?php printf( esc_attr__( 'Permalink to %s', 'twentyten' ), the_title_attribute( 'echo=0' ) ); ?>" rel="bookmark"><?php the_title(); ?></a></h2>
		<div class="entry-meta"><?php the_time('F jS, Y') ?></div>
        <span>
        <?php comments_popup_link( __( 'Leave a comment', 'twentyten' ), __( '1 Comment', 'twentyten' ), __( '% Comments', 'twentyten' ) ); ?><br />
        Posted In: <?php the_category(', ') ?>
        </span>
           </a>
	</div>
 </div>
      <?php
    endwhile;
  }
}
?>
        <?php wp_reset_query(); ?>
</div>


				<?php comments_template( '', true ); ?>

<?php endwhile; // end of the loop. ?>
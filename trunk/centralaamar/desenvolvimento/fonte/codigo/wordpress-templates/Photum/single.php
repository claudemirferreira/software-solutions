<?php
/**
 * The Template for displaying all single posts.
 *
 * @package WordPress
 * @subpackage Twenty_Ten
 * @since Twenty Ten 1.0
 */

get_header(); ?>

		<div id="container">
			<div id="content" role="main">

			<?php
			/* Run the loop to output the post.
			 * If you want to overload this in a child theme then include a file
			 * called loop-single.php and that will be used instead.
			 */
			get_template_part( 'loop', 'single' );
			?>
                        <div class="clr"></div>
              	<div id="footer" role="contentinfo">
           		<div id="site-info">
				Copyright &copy; <?php echo date("Y"); ?> <a href="<?php echo home_url( '/' ) ?>" title="<?php echo esc_attr( get_bloginfo( 'name', 'display' ) ); ?>" rel="home">
					<?php bloginfo( 'name' ); ?>
				</a>. All rights reserved.
			</div><!-- #site-info -->
	</div><!-- #footer -->   
			</div><!-- #content -->
            <?php get_sidebar(); ?>
		</div><!-- #container -->

<?php get_footer(); ?>

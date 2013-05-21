<?php
/**
 * The Blog Page widget areas.
 *
 * @package WordPress
 * @subpackage Twenty_Ten
 * @since Twenty Ten 1.0
 */
?>

<?php
	/* The footer widget area is triggered if any of the areas
	 * have widgets. So let's check that first.
	 *
	 * If none of the sidebars have widgets, then let's bail early.
	 */
	if (   ! is_active_sidebar( 'blog-page-widget-area'  )
	)
		return;
	// If we get this far, we have widgets. Let do this.
?>

			<div id="footer-widget-area" role="complementary">
<?php if ( is_active_sidebar( 'blog-page-widget-area' ) ) : ?>
				<div id="first" class="widget-area">
					<ul class="xoxo">
						<?php dynamic_sidebar( 'blog-page-widget-area' ); ?>
					</ul>
				</div><!-- #first .widget-area -->
<?php endif; ?>
			</div><!-- #footer-widget-area -->

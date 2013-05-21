<?php
/**
 * The template for displaying the footer.
 *
 * Contains the closing of the id=main div and all content
 * after.  Calls sidebar-footer.php for bottom widgets.
 *
 * @package WordPress
 * @subpackage Twenty_Ten
 * @since Twenty Ten 1.0
 */
?>
	</div><!-- #main -->
    </div><!-- #wrapper -->
<script src="<?php bloginfo('template_url'); ?>/js/jquery.masonry.min.js"></script>
<script>
  $(function(){
    
    console.time('masonry');
    $('#move').masonry({
      itemSelector: '.item',
      columnWidth: 30,
      isAnimated: true
    }).masonry().masonry();
    console.timeEnd('masonry');
    
  });
</script>
<?php
	/* Always have wp_footer() just before the closing </body>
	 * tag of your theme, or you will break many plugins, which
	 * generally use this hook to reference JavaScript files.
	 */

	wp_footer();
?>
</body>
</html>

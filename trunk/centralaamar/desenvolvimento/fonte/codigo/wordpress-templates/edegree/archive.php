<?php get_header(); ?>
<div class="page">
		<?php if (have_posts()) : ?>

 	  <?php $post = $posts[0]; ?>
 	  <?php /* If this is a category archive */ if (is_category()) { ?>
		<p class="catheader catcenter"><?php _e('Arquivo da categoria')?> &#8216;<?php single_cat_title(); ?>&#8217;</p>
 	  <?php /* If this is a tag archive */ } elseif( is_tag() ) { ?>
		<p class="catheader catcenter"><?php _e('Posts marcados com')?> &#8216;<?php single_tag_title(); ?>&#8217;</p>
 	  <?php /* If this is a daily archive */ } elseif (is_day()) { ?>
		<p class="catheader catcenter"><?php _e('Arquivo de')?> <?php the_time('d F Y'); ?></p>
 	  <?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
		<p class="catheader catcenter"><?php _e('Arquivo de')?> <?php the_time('F Y'); ?></p>
 	  <?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
		<p class="catheader catcenter"><?php _e('Arquivo de')?> <?php the_time('Y'); ?></p>
	  <?php /* If this is an author archive */ } elseif (is_author()) { ?>
		<p class="catheader catcenter"><?php _e('Arquivo do Autor')?></p>
 	  <?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
		<p class="catheader catcenter"><?php _e('Arquivo do Blog')?></p>
 	  <?php } ?>

		<?php while (have_posts()) : the_post(); ?>
		<div class="posts">
				<h2 id="post-<?php the_ID(); ?>"><a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title_attribute(); ?>"><?php the_title(); ?></a></h2>

				<div class="page-content">
                	<small><?php the_time(get_option('date_format')) ?></small><br />
					<?php the_excerpt(); ?>
				</div>

				<p class="meta"><?php the_tags('Etiquetas: ', ', ', '<br />'); ?> <span class="folder-icon"><?php _e('Colocado em')?></span> <?php the_category(', ') ?> | <?php edit_post_link('Editar', '', ' | '); ?>  <span class="comment-icon"><?php comments_popup_link('Sem Comentários &#187;', '1 Comentário &#187;', '% Comentários &#187;'); ?></span></p>

			</div>

		<?php endwhile; ?>
        
		<?php if(isset($paged)):?>
        <div class="navigation">
            <p class="alignleft"><?php previous_posts_link('&laquo; Anterior'); ?></p>
            <p class="alignright"><?php next_posts_link('Seguinte &raquo;'); ?></p>
            <div class="recover"></div>
        </div>
        <?php endif; ?>
        
	<?php else :

		if ( is_category() ) { // If this is a category archive
			printf("<h2 class='center'>Lamentamos, mas a categoria %s ainda não tem posts.</h2>", single_cat_title('',false));
		} else if ( is_date() ) { // If this is a date archive
			echo("<h2>Lamentamos, mas nesta data não foram publicados posts.</h2>");
		} else if ( is_author() ) { // If this is a category archive
			$userdata = get_userdatabylogin(get_query_var('author_name'));
			printf("<h2 class='center'>Lamentamos, mas %s ainda não publicou artigos.</h2>", $userdata->display_name);
		} else {
			echo("<h2 class='center'>Lamentamos, mas não foram encontrados posts.</h2>");
		}

	endif;
?>
</div>
<?php get_footer(); ?>

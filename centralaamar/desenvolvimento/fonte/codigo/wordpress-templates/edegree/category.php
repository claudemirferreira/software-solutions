<?php get_header(); ?>
<div class="page">
    <h2 class="catheader catcenter">
		<?php single_cat_title(); ?>
    </h2>
	<?php if (have_posts()) : ?>
    <?php while (have_posts()) : the_post(); ?>
    <div class="page-content">
		<h3><a href="<?php the_permalink() ?>" title="<?php the_title(); ?>"><?php the_title(); ?></a></h3>
		<div class="meta">
					<?php _e("Publicado por"); ?> <?php the_author_posts_link(); ?> <?php _e("em"); ?> <?php the_time(get_option('date_format')); ?> <?php _e("às"); ?> <?php the_time('g:i a'); ?> <?php edit_post_link('Editar'); ?>
				</div>
					<?php getImage('1'); ?>

		<?php the_excerpt(); ?>
    </div>
    
    <?php endwhile; ?>

		<?php if(isset($paged)):?>
        <div class="navigation">
            <p class="alignleft"><?php previous_posts_link('&laquo; Anterior'); ?></p>
            <p class="alignright"><?php next_posts_link('Seguinte &raquo;'); ?></p>
            <div class="recover"></div>
        </div>
        <?php endif; ?>
            
    <?php else : ?>
        <h2 class="catheader"><?php _e("Categoria Não Encontrada")?></h2>
        <p><?php _e('Lamentamos, mas a categoria que procura não foi encontrada. Por favor utilize a pesquisa para chegar ao que procura.')?></p>
        <?php include('searchform.php') ?>
    <?php endif; ?>
</div>
<?php get_footer(); ?>

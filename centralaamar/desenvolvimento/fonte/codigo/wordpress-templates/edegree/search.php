<?php get_header(); ?>

<div class="page">
	<?php if (have_posts()) : ?>
    
        <h1 class="catheader"><?php _e('Resultados da Pesquisa')?></h1>
        
        <?php while (have_posts()) : the_post(); ?>
            <div class="posts">
            <h2><a href="<?php the_permalink() ?>" title="<?php _e('Ler o post')?><?php the_title(); ?>"><?php the_title(); ?></a></h2>
            <div class="meta">
                        <?php _e('By')?> <?php the_author() ?>
                    </div>
            <?php the_excerpt(); ?>
            </div>
        <?php endwhile; ?>

		<?php if(isset($paged)):?>
        <div class="navigation">
            <p class="alignleft"><?php previous_posts_link('&laquo; Página Anterior'); ?></p>
            <p class="alignright"><?php next_posts_link('Página Seguinte &raquo;'); ?></p>
            <div class="recover"></div>
        </div>
        <?php endif; ?>
            
    <?php else : ?>
    
        <h2 class="catheader"><?php _e("Lamentamos, mas não foram encontrados resultados para a sua pesquisa")?></h2>
        <p><?php _e('Tente novamente, mas utilize outras palavras.')?></p>
        <?php include('searchform.php') ?>
    
	<?php endif; ?>

</div>

<?php get_footer(); ?>

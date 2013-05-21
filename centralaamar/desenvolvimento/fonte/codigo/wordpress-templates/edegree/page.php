<?php get_header(); ?>
	<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
		
		<?php //Homepage 
		/*if(is_front_page()) {
		?>
		<div class="home-content">
			<?php the_content()?>
		</div>
		<?php
		} else {*/ //Subpages ?>
			<div class="page">
				<h2 class="catheader"><?php the_title(); ?></h2> <?php edit_post_link('Editar', '<span class="editpost">', '</span>'); ?>
				
				<div class="page-content">
						<?php the_content('<p class="serif">Continuar a Ler &raquo;</p>'); ?>
						<?php wp_link_pages(array('before' => '<p><strong>Páginas:</strong> ', 'after' => '</p>', 'next_or_number' => 'number')); ?>
				</div>
                
                <?php comments_template(); ?>
			</div>
		<?php //} ?>
		
	<?php endwhile; endif; ?>
<?php get_footer(); ?>
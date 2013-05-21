<?php 
global $more; 
$template_url = get_bloginfo('template_url');
?>
<div id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
    <div class="entry_header">
        <p class="entry-date"><?php the_time('M y') ?><br /><span class="date"><?php the_time('j')?></span></p>
        <?php echo (is_home()) ? '<h2 class="home">' : '<h1>'?><a href="<?php the_permalink() ?>"><?php the_title(); ?></a> <?php edit_post_link('Editar', '<span class="editpost">', '</span>'); ?><?php echo (is_home()) ? '</h2>' : '</h1>'?>
        <?php if ($post->comment_status != 'closed' && !post_password_required()):?>
        	<div class="comment-bubble"><span><?php comments_popup_link('<span class="nocomment">Sem Comentários</span>', '&nbsp;1 Comentário', '% Comentários'); ?></span></div>
        <?php endif;?>
        <div class="recover"></div>
    </div>

				
    <div class="entry_content">
        <?php $more = 0;
		the_content('<span class="readmore">Continuar a Ler '. get_the_title('', '', false). ' &raquo;</span>', FALSE);
		wp_link_pages(array('before'=>'<p class="pages-icon">' . __('Páginas:'), 'after'=>'</p>'));
		?>
        
        <div class="postedinfo"><?php the_tags('<span class="tag-meta">Etiquetas: ', ', ', '</span><br />'); ?> <span class="folder-icon"><?php _e('Colocado em')?></span> <span class="categories"><?php the_category(' ') ?></span> <?php _e('por')?> <span class="usr-meta"><?php the_author() ?></span>.
        <span class="comment-icon"><?php comments_popup_link('Sem Comentários', '1 Comentário', '% Comentários')?></span>
        <?php if (isset($options['tags'])) : ?><span class="tags"><?php the_tags('', ', ', ''); ?></span><?php endif; ?></div>
    </div>
</div>
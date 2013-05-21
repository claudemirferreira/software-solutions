<?php
if (!empty($_SERVER['SCRIPT_FILENAME']) && 'comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
	die (_e('Please do not load this page directly. Thanks!'));

if ( post_password_required() ) { ?>
	<p class="nocomments"><?php _e('Este post está protegido por uma palavra-passe. Insira-a para ver os comentários.')?></p>
<?php
	return;
}
?>

<?php if ( have_comments() ) : ?>
	<h3 class="commentheading"><?php comments_number('Seja o primeiro a comentar ', '1 Comentário para', '% Comentários para' );?> "<?php the_title(); ?>"</h3>

	<ul id="comments" class="commentlist">
		<?php wp_list_comments('callback=tbf2_comment'); ?>
	</ul>

    <?php if(get_option('page_comments') != 0): ?>
    <div class="navigation">
        <div class="alignleft"><?php previous_comments_link(__('&laquo; Comentários Antigos')) ?></div>
		<div class="alignright"><?php next_comments_link(__('Comentários Recentes &raquo;')) ?></div>
        <div class="recover"></div>
    </div>
    <?php endif; ?>
    
 <?php else : // this is displayed if there are no comments so far ?>

	<?php if ('open' == $post->comment_status) : ?>
		<!-- If comments are open, but there are no comments. -->

	 <?php else : // comments are closed ?>
		<!-- If comments are closed. -->
		<?php echo (!is_page()) ? '<p class="nocomments">Os comentários estão encerrados.</p>' : '' ?>

	<?php endif; ?>
<?php endif; ?>


<?php if ('open' == $post->comment_status) : ?>
<div id="respond">
    <h5><?php comment_form_title( 'Deixe o seu comentário', 'Deixe o seu comentário para %s' ); ?></h5>
    <div class="cancel-comment-reply">
	<?php cancel_comment_reply_link(); ?>
    </div>

		<?php if ( get_option('comment_registration') && !$user_ID ) : ?>
        <p><a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php echo urlencode(get_permalink()); ?>"><?php _e('Autentifique-se')?></a> <?php _e('para publicar o seu comentário.')?></p>
        <?php else : ?>

            <form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">
            
				<?php if ( $user_ID ) : ?>
                
                	<p><?php _e('Autentificado como')?> <a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php" class="usr-meta"><?php echo $user_identity; ?></a>. <a href="<?php echo wp_logout_url(get_permalink()); ?>" title="<?php _e('Sair desta Conta')?>" class="comment-reply-login"><?php _e('Sair')?> &raquo;</a></p>
                
                <?php else : ?>
    
                    <p><input type="text" name="author" id="author" class="formfield" value="<?php echo $comment_author; ?>" size="22" tabindex="1" />
                    <label for="author"><?php _e('Nome')?> <?php if ($req) echo "(requerido)"; ?></label></p>
                    
                    <p><input type="text" name="email" id="email" class="formfield" value="<?php echo $comment_author_email; ?>" size="22" tabindex="2" />
                    <label for="email"><?php _e('Email (não será publicado)')?> <?php if ($req) echo "(requerido)"; ?></label></p>
                    
                    <p><input type="text" name="url" id="url" class="formfield" value="<?php echo $comment_author_url; ?>" size="22" tabindex="3" />
                    <label for="url"><?php _e('Website')?></label></p>
                
                <?php endif; ?>
    
                <textarea name="comment" id="comment" class="formfield" cols="60" rows="6" tabindex="4"></textarea>
                
                <p class="submit-line"><span class="btn"><span><input name="submit" type="submit" id="submit" tabindex="5" value="<?php _e('Enviar Comentário')?>" /></span></span>
                    <?php comment_id_fields(); ?>
                </p>
                <?php do_action('comment_form', $post->ID); ?>
            
            </form>
        
        <?php endif; // If registration required and not logged in ?>

</div>
<?php endif; ?>
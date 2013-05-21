			<div class="recover"></div>
        </div><!--End left-col-->
              
			  <?php get_sidebar(1); ?>
              
              <div class="recover"></div>
			</div><!-- #container-shoulder -->
        </div><!-- #Container-->
         
 
        <div id="globalnav">                
        	<div id="navpocket">                
                <ul id="nav"<?php echo (get_option('tbf2_search_header') == "no") ? ' class="nav-wide"' : '';?>>                
                    <?php if(get_option('tbf2_nav_hide_home') != 'yes') : ?>
                    <li<?php if(is_home()) echo ' class="current_page_item"';?>><a href="<?php echo get_option('home'); ?>" rel="nofollow"><?php _e('Home')?></a></li>
                    <?php endif; ?>
                    
                    <?php wp_list_pages('title_li=&sort_column=menu_order&exclude='.get_option('tbf2_exclude_pages')); ?>
                    
                    <?php /* Uncomment this if you want to show categories in the top navigation
                    <li><a rel="nofollow" href="#"><?php _e("Topics"); ?></a>
                        <ul><?php wp_list_categories('title_li=&depth=4&orderby=name'); ?></ul>
                    </li>*/?>
                </ul>
        	</div>
        </div>
    </div><!--End shadow-->
  </div><!--End wrapper-->
  
      <div id="footer" <?php echo (get_option('tbf2_footer_image_file')) ? 'style="background:url('.get_option('tbf2_footer_image_file'). ') no-repeat center top"' : ''?>>
        <div class="footer-content">
            <div class="footer-widget">
                <ul class="footerlinks">
                    <?php if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar("Footer Left") ) : ?>
                    <li><h2>Footer Left</h2>Ocupe este espaço através do Widget "Footer Left".<br /><br />Pode colocar neste espaço as categorias, últimos comentários, links, etc.</li>
                    <?php endif; ?>	
                </ul>
            </div>
            <div class="footer-widget">
                <ul class="footerlinks">
                    <?php if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar("Footer Middle") ) : ?>
                    <li><h2>Footer Middle</h2>Ocupe este espaço através do Widget "Footer Middle".<br /><br />Pode colocar neste espaço as categorias, últimos comentários, links, etc.</li>
                    <?php endif; ?>	
                </ul>
            </div>
            <div class="footer-widget">
                <ul class="footerlinks">
                    <?php if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar("Footer Right") ) : ?>
                    <li><h2>Footer Right</h2>Ocupe este espaço através do Widget "Footer Right".<br /><br />Pode colocar neste espaço as categorias, últimos comentários, links, etc.</li>
                    <?php endif; ?>	
                </ul>
            </div>
          <div class="recover"></div>
          
          <span id="copyright"><span class="alignleft"><?php _e('Copyright &copy; ')?>
            <script type="text/javascript">
			/* <![CDATA[ */
			var startCopyrightYear = <?php echo (get_option('tbf2_copy_year')) ? get_option('tbf2_copy_year') : "''" ?>;
			if(!startCopyrightYear) {
				var d=new Date();
				startCopyrightYear = d.getFullYear();
			}
			printCopyrightYears(startCopyrightYear)
			/* ]]> */
            </script>
            <?php echo bloginfo('site_name')?></span><span id="footer-tag"> | &nbsp; Tema <a href="http://www.topblogformula.com/wordpress-business-themes/edegree" target="_blank">eDegree</a> traduzido por <a href="http://www.wptotal.com" target="_blank">WP Total</a> | &nbsp; 
            <?php if(is_user_logged_in()):?>
                <a href="<?php echo wp_logout_url(get_permalink()); ?>" title="<?php echo _e('Sair') ?>"><?php echo _e('Sair'); ?></a>
            <?php else:?>
                <a href="<?php echo bloginfo('url')?>/wp-login.php">Entrar</a>
            <?php endif;?>
          </span>
    
        </div>
      </div><!--End footer-->

</div><!--End bg-->


<?php //Login Bar at the top 
		if(get_option('tbf2_user_login') == "yes") { ?>
		 <div id="login">
    <?php
		global $user_identity, $user_level;
		if (is_user_logged_in()) { ?>
            <ul>
                <li><span style="float:left;"><?php _e('Autentificado como:')?> <strong><?php echo $user_identity ?></strong></span></li>
				<li><a href="<?php bloginfo('url'); ?>/wp-admin">Links Rápidos</a></li>
                <?php if ( $user_level >= 1 ) { ?>
                <li class="dot"><a href="<?php bloginfo('url') ?>/wp-admin/post-new.php">Novo Post</a></li>
                <li class="dot"><a href="<?php bloginfo('url') ?>/wp-admin/page-new.php">Nova Página</a></li>
                <li class="dot"><a href="<?php bloginfo('url') ?>/wp-admin/widgets.php">Widgets</a></li>
                <li class="dot"><a href="<?php bloginfo('url') ?>/wp-admin/admin.php?page=tbf-design.php">Opções do Tema</a></li>
			<?php } ?>
                <li class="dot"><a href="<?php bloginfo('url') ?>/wp-admin/profile.php">Perfil</a></li>
				<li class="dot"><a href="<?php echo wp_logout_url(get_permalink()); ?>" title="<?php _e('Sair') ?>"><?php _e('Sair'); ?></a></li>
             </ul>
            <?php 
		} else {
			echo '<ul>';
			echo '<li><a href="'; echo bloginfo("url"); echo '/wp-login.php">Entrar</a></li>';
			if (get_option('users_can_register')) { ?>
				<li class="dot"><a href="<?php echo site_url('wp-login.php?action=register', 'login') ?>"><?php _e('Registar') ?></a> </li>
                
            <?php 
			}
			echo "</ul>";
		} ?> 
    </div>
<?php } ?>

<?php wp_footer(); ?>

</body>
</html>
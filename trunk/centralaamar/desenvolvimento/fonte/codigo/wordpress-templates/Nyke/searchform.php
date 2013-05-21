<h3 class=" sidetitl"><?php _e('Search'); ?></h3>
<div id="search">
<?php $search_text = "Search my site..."; ?> 
<form method="get" id="searchform" action="<?php bloginfo('home'); ?>/"> 
<input type="text" value="<?php echo $search_text; ?>" name="s" id="s" onblur="if (this.value == ''){this.value = '<?php echo $search_text; ?>';}" onfocus="if (this.value == '<?php echo $search_text; ?>'){this.value = '';}" /> 
<input id="searchsubmit" type="submit" value="Search" />
	</form>
	
</div>
	<div class='clear'></div>
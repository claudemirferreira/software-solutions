<?php get_header(); ?>

<div id="content" class="scover " >


<?php if (have_posts()) : ?>
<h2 class="pagetitle">Search Results</h2>
<?php while (have_posts()) : the_post(); ?>
		
<div class="sboxs" id="post-<?php the_ID(); ?>">

<h2 class="stitle"><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title(); ?>"><?php the_title(); ?></a></h2>

<p style="line-height:18px; paddding:5px 0px; color:#333;"><?php the_content_rss('more_link_text', TRUE, '', 30); ?></p>
<div class="clear"></div>
<span class="searchmeta"> Posted by <?php the_author(); ?> on <?php the_time('F - j - Y'); ?> | <?php comments_popup_link('0 Comment', '1 Comment', '% Comments'); ?></span>	

</div>

<?php endwhile; ?>
<?php getpagenavi(); ?>
<?php else : ?>




<div class="entry" style="padding:10px 15px;">

<b>Your search - <?php the_search_query();?> - did not match any entries.</b>

<p>Suggestions:</p>
<ul>
   <li>  Make sure all words are spelled correctly.</li>
   <li>  Try different keywords.</li>
   <li>  Try more general keywords.</li>
</ul>
			
</div>

<?php endif; ?>

<div class="clear"></div>

</div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>


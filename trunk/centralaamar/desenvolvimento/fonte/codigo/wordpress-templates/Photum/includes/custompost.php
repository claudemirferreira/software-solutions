<?php
add_action( 'init', 'create_my_post_types' );

function create_my_post_types() {
	register_post_type( 'photo',
		array(
			'labels' => array(
	'name' => __( 'Photos' ),
	'singular_name' => __( 'Photo' ),
	'add_new' => __( 'Add New' ),
	'add_new_item' => __( 'Add New Photo' ),
	'edit' => __( 'Edit' ),
	'edit_item' => __( 'Edit Photo' ),
	'new_item' => __( 'New Photo' ),
	'view' => __( 'View Photo' ),
	'view_item' => __( 'View Photo' ),
	'search_items' => __( 'Search Photos' ),
	'not_found' => __( 'No photos found' ),
	'not_found_in_trash' => __( 'No photos found in Trash' ),
	'parent' => __( 'Parent Photoo' ),
),
			'public' => true,
			'menu_position' => 5,
			'query_var' => true,
			'supports' => array( 'title', 'editor', 'excerpt', 'custom-fields', 'thumbnail', 'comments' ),
			'taxonomies' => array( 'post_tag', 'category'),
			'can_export' => true,
			'rewrite' => array( 'slug' => 'photo', 'with_front' => false ),
		)
	);
}
?>
<?
register_taxonomy("location", array("photo"), array("hierarchical" => true, "label" => "Locations", "singular_label" => "Location", "rewrite" => true));
?>
<?
register_taxonomy("equipment", array("photo"), array("hierarchical" => true, "label" => "Equipments Used", "singular_label" => "Equipment Used", "rewrite" => true));
?>
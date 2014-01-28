package br.com.ss.data.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-26T23:48:21.068-0200")
@StaticMetamodel(Post.class)
public class Post_ {
	public static volatile SingularAttribute<Post, Integer> postId;
	public static volatile SingularAttribute<Post, String> title;
	public static volatile SingularAttribute<Post, Date> postDate;
}

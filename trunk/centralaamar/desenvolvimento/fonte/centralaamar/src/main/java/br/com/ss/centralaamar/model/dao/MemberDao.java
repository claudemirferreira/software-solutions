package br.com.ss.centralaamar.model.dao;

import java.util.List;

import br.com.ss.centralaamar.model.entity.Member;

public interface MemberDao {
	public Member findById(Long id);

	public Member findByEmail(String email);

	public List<Member> findAllOrderedByName();

	public void register(Member member);
}

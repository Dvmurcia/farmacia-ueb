package com.unbosque.dao;

import java.util.List;
import com.unbosque.entity.Auditoria;

public interface AuditoriaDAO {

	public void save(Auditoria auditoria);

	public Auditoria getAuditoria(long id);

	public List<Auditoria> list();

}

package domain.service;

import java.util.List;

import domain.model.Profile;

public interface InstrumentService {
	// TODO: Implement

	Int incrementScore()
	List<Instrument> getAll();
	void update(Instrument instrument);
	void create(Instrument instrument);
	Instrument get(Long instrumentId);
	Long count();
}
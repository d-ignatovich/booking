package rest.service;

import org.springframework.stereotype.Service;

import rest.dto.RecordDTO;
import rest.persistence.repository.RecordRepository;
import rest.persistence.entity.Record;
import rest.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<RecordDTO> createRecord(RecordDTO recordDTO, User user) {
        Record record = new Record();
        record.setId(UUID.fromString(recordDTO.getId()));
        record.setTitle(recordDTO.getTitle());
        record.setAddress(recordDTO.getAddress());
        record.setBerth(recordDTO.getBerth());
        record.setRent(recordDTO.getRent());
        record.setDescription(recordDTO.getDescription());
        record.setImage(recordDTO.getImage());
        record.setUser(user);
        recordRepository.save(record);
        return getAllRecords();
    }

    public List<RecordDTO> getAllRecords() {
        List<Record> records = recordRepository.getAllRecords();
        List<RecordDTO> resultList = new ArrayList<>();
        for (Record record : records) {
            RecordDTO recordDTO = new RecordDTO();
            recordDTO.setId(record.getId().toString());
            recordDTO.setTitle(record.getTitle());
            recordDTO.setAddress(record.getAddress());
            recordDTO.setBerth(record.getBerth());
            recordDTO.setRent(record.getRent());
            recordDTO.setDescription(record.getDescription());
            recordDTO.setImage(record.getImage());
            recordDTO.setUserId(record.getUser().getId().toString());
            resultList.add(recordDTO);
        }
        return resultList;
    }

    public void findRecordById(UUID id) {
        Optional<Record> record = recordRepository.findById(id);
        ArrayList<Record> res = new ArrayList<>();
        record.ifPresent(res :: add);
    }
    public void removeRecordById(UUID id) {
    }
}
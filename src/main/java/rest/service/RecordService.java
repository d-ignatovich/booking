package rest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import rest.dto.RecordDTO;
import rest.persistence.repository.RecordRepository;
import rest.persistence.entity.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public ModelAndView createRecord(RecordDTO recordDTO) {
        Record record = new Record();
        record.setId(UUID.randomUUID());
        record.setTitle(recordDTO.getTitle());
        record.setAddress(recordDTO.getAddress());
        record.setBerth(recordDTO.getBerth());
        record.setRent(recordDTO.getRent());
        record.setDescription(recordDTO.getDescription());
        if (!recordDTO.getImage().equals("")) {
            record.setImage(recordDTO.getImage());
        } else {
            record.setImage("/tmp/default.jpg");
        }
        recordRepository.save(record);
        return getAllRecords();
    }

    public ModelAndView getAllRecords() {
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
            resultList.add(recordDTO);
        }
        return createAndFillModel(resultList);
    }

    private ModelAndView createAndFillModel(List<RecordDTO> recordDTOs) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        modelAndView.getModel().put("listRecords", recordDTOs);
        modelAndView.setViewName("records-page");
        return modelAndView;
    }

    public void removeRecordById(UUID id) {
        recordRepository.deleteById(id);
    }
}
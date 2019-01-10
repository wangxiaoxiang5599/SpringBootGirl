package com.springboot.girl.service;

import com.springboot.girl.enums.ResultEnum;
import com.springboot.girl.exception.GirlException;
import com.springboot.girl.repository.GirlRepository;
import com.springboot.girl.domain.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

	@Autowired
	private GirlRepository girlRepository;

	@Transactional
	public void insertTwo() {
		Girl girlA = new Girl();
		girlA.setCupSize("A");
		girlA.setAge(18);
		girlA.setMoney(1900l);
		girlRepository.save(girlA);

		Girl girlB = new Girl();
		girlB.setCupSize("B");
		girlB.setAge(18);
		girlB.setMoney(2000l);
		girlRepository.save(girlB);
	}

	public void getAge(Integer id) throws Exception {
		Girl girl = girlRepository.findById(id).orElse(null);
		Integer age = girl.getAge();
		if (age < 10 && age > 0) {
			throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
		} else if (age > 10 && age < 16) {
			throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
		}

	}
}

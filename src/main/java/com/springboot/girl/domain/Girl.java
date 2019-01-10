package com.springboot.girl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Girl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String cupSize;

	@Min(value = 18, message = "未成年少女禁止入门")
	private Integer age;

	@NotNull(message = "金额必传")
	private Long money;

	public Girl() {
	}

	public Integer getId() {
		return id;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCupSize() {
		return cupSize;
	}

	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Girl{" + "id=" + id + ", cupSize='" + cupSize + '\'' + ", age=" + age + ", money=" + money + '}';
	}

}

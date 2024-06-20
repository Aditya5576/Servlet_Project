package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class FoodItem {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	int id;
	String name;
	double price;
	int stock;
	String type;
	
	@Lob
	byte [] picture;
	
	@ManyToOne
	Hotel hotel;

}

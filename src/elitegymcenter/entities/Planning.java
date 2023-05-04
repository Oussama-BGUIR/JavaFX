/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;

import java.sql.Date;

/**
 *
 * @author ousam
 */public class Planning {
	    private int id;
	    private String semaine;
	    private String description,image_code_qr ;

	    public Planning(int id, String semaine, String description, String image_code_qr) {
			super();
			this.id = id;
			this.semaine = semaine;
			this.description = description;
			this.image_code_qr = image_code_qr;
		}

		public String getImage_code_qr() {
			return image_code_qr;
		}

		public void setImage_code_qr(String image_code_qr) {
			this.image_code_qr = image_code_qr;
		}

		 
		public Planning(String semaine, String description, String image_code_qr) {
			super();
			this.semaine = semaine;
			this.description = description;
			this.image_code_qr = image_code_qr;
		}

		@Override
		public String toString() {
			return "Planning [id=" + id + ", semaine=" + semaine + ", description=" + description + ", image_code_qr="
					+ image_code_qr + "]";
		}

		public Planning(int id, String semaine, String description) {
	        this.id = id;
	        this.semaine = semaine;
	        this.description = description;
	    }

	    public Planning(String semaine, String description) {
			super();
			this.semaine = semaine;
			this.description = description;
		}

		public Planning() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getSemaine() {
	        return semaine;
	    }

	    public void setSemaine(String semaine) {
	        this.semaine = semaine;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }
	}
 



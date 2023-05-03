/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author ousam
 */public class Cours {
	    private int id;
	    private int planningId;
	    private String nom;
	    private LocalDateTime duree;
	    private String salle,planningDescription,image,planningQR;

	    public Cours(int id, int planningId, String nom, LocalDateTime duree, String salle, String planningDescription,
				String image, String planningQR) {
			super();
			this.id = id;
			this.planningId = planningId;
			this.nom = nom;
			this.duree = duree;
			this.salle = salle;
			this.planningDescription = planningDescription;
			this.image = image;
			this.planningQR = planningQR;
		}

		public String getPlanningQR() {
			return planningQR;
		}

		public void setPlanningQR(String planningQR) {
			this.planningQR = planningQR;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getPlanningDescription() {
			return planningDescription;
		}

		public void setPlanningDescription(String planningDescription) {
			this.planningDescription = planningDescription;
		}

		public Cours(int planningId, String nom, LocalDateTime duree, String salle,String img) {
			super();
			this.planningId = planningId;
			this.nom = nom;
			this.duree = duree;
			this.salle = salle;
			this.image=img;
		}

		public Cours(String nom, String salle) {
			super();
			this.nom = nom;
			this.salle = salle;
		}

		public Cours(int id, int planningId, String nom, LocalDateTime duree, String salle) {
	        this.id = id;
	        this.planningId = planningId;
	        this.nom = nom;
	        this.duree = duree;
	        this.salle = salle;
	    }

	    public Cours() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getPlanningId() {
	        return planningId;
	    }

	    public void setPlanningId(int planningId) {
	        this.planningId = planningId;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public LocalDateTime getDuree() {
	        return duree;
	    }

	    public void setDuree(LocalDateTime localDateTime) {
	        this.duree = localDateTime;
	    }

	    public String getSalle() {
	        return salle;
	    }

	    public void setSalle(String salle) {
	        this.salle = salle;
	    }

		@Override
		public String toString() {
			return "Cours [id=" + id + ", planningId=" + planningId + ", nom=" + nom + ", duree=" + duree + ", salle="
					+ salle + ", planningDescription=" + planningDescription + ", image=" + image + ", planningQR="
					+ planningQR + "]";
		}

	 
	}

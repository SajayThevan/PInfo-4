package domain.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Iterator;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import domain.model.Ingredients;
import domain.model.Recipe;
import domain.model.RecipeDTO;
import domain.model.Ratings;


@ApplicationScoped
public class RecipeServiceImpl implements RecipeService {

	@PersistenceContext(unitName = "RecipePU")
	private EntityManager em;


	public RecipeServiceImpl() {
	}

	public RecipeServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}

	@Override
	public void addRecipe(Recipe r) {
		if (r.getId() != null) {
			throw new IllegalArgumentException("Recipe already exists : " + r.getId());
		}
		em.persist(r);
	}

	@Override
	public void addRating(long id,int rate) {
		Recipe r = em.find(Recipe.class, id);
		Ratings rating = new Ratings();
		r.updateRating(rating);
		em.merge(r);
	}

	@Override
	public ArrayList<RecipeDTO> getRecipesForProfil(long id){
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r WHERE r.authorID = :authorID", Recipe.class);
		query.setParameter("authorID", id);
		List<Recipe> tmp = query.getResultList();
		ArrayList<RecipeDTO> listToReturn = new ArrayList();
        Iterator it = tmp.iterator();
        while (it.hasNext()) {
        	Recipe r = (Recipe) it.next();
        	listToReturn.add(new RecipeDTO(r.getId(),r.getName(),r.getIngredients(),r.getAuthorID(),r.getRatings()));
        }
        return listToReturn;
	}

	@Override
	public ArrayList<RecipeDTO> getRecipiesIdForProfiles(long id){
		TypedQuery<Long> query = em.createQuery("SELECT r.id FROM Recipe r WHERE r.authorID = :authorID",Long.class);
		query.setParameter("authorID", id);
		List<Long> ids = query.getResultList();
		ArrayList<RecipeDTO> tr = new ArrayList<RecipeDTO>();
		for (Long idTmp: ids) {
			Recipe r = em.find(Recipe.class, idTmp);
			tr.add(new RecipeDTO(r.getId(),r.getName(),r.getIngredients(),r.getAuthorID(),r.getRatings()));
		}
		return tr;
	}

	@Override
	public void addComment(String comment, long id) {
		Recipe r = em.find(Recipe.class, id);
		r.addComent(comment);
		em.merge(r);
	}

	@Override
	public void removeRecipe(long id) {
		Recipe r = em.find(Recipe.class, id);
		em.remove(r);
	}

	@Override
	public Recipe getRecipe(long id) {
		Recipe r = em.find(Recipe.class, id);
		return r;
	}

	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Recipe.class)));
		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public ArrayList<RecipeDTO> getRecipeWithIngredientID(ArrayList<Long> ing_id){
		ArrayList <RecipeDTO> tr = new ArrayList<RecipeDTO>();
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
		List<Recipe> rl = query.getResultList();
		for(Recipe r: rl ) {
			Set<Ingredients> ing = r.getIngredients();
			ArrayList<Long> containedIngId = new ArrayList<Long>();
			for(Ingredients i: ing) {
				containedIngId.add(i.getIngredientID());
			}
			System.out.println("-------------------------");
			System.out.println(r.getId());
			System.out.println(containedIngId);
			System.out.println(ing_id);
			if(containedIngId.containsAll(ing_id)){
				tr.add(new RecipeDTO(r.getId(),r.getName(),r.getIngredients(),r.getAuthorID(),r.getRatings()));
			}
		}
	return tr;
	}

	@Override
	public ArrayList<RecipeDTO> getTendancies(){
		ArrayList <RecipeDTO> tr = new ArrayList<RecipeDTO>();
		int numberOfTendancies = 20;
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
		List<Recipe> rl = query.getResultList();
		if( rl.size() > 0) {
			ArrayList<Pair<Long,Float>> tmpPair = new ArrayList<Pair<Long,Float>>(); //Array actualise de pair <id,mean>
			Recipe r1 = rl.get(0);
			float mean1 = 0;
			for(Ratings g: r1.getRatings()) {
				mean1 += g.getRate();
			}
			mean1 = mean1 / r1.getRatings().size();
			Pair<Long, Float> pairOfTheRecipe1 = new Pair<Long, Float>(r1.getId(),mean1);
			tmpPair.add(pairOfTheRecipe1);
			for(int u = 1; u < rl.size(); u++)
			{
				//On a déjà mis la première recette:
				Recipe r = rl.get(u);
				float recipeMean = 0;
				for(Ratings g: r.getRatings()) {
					recipeMean += g.getRate();
				}
				boolean recipeAdded = false;
				recipeMean = recipeMean / r.getRatings().size();
				ArrayList<Pair<Long,Float>> tmp = new ArrayList<Pair<Long,Float>>();
				int i = 0;
				boolean flag = true;
				while (flag) {
					Pair<Long, Float> p;
					p = tmpPair.get(i);
					if (recipeMean < p.getValue1()){
						tmp.add(tmpPair.get(i));
					}else {
						Pair<Long, Float> pairOfTheRecipe = new Pair<Long, Float>(r.getId(),recipeMean);
						tmp.add(pairOfTheRecipe);
						recipeAdded = true;
						flag = false;
						//i += 1;
					}
					i += 1;
					if (i == tmpPair.size()){
						flag = false;
					}
				}
			if (recipeAdded) {
					for (int k = i; k < Math.min((tmpPair.size() + 1),numberOfTendancies) ; k++ ) {
						tmp.add(tmpPair.get(k-1)); //On ajoute les éléments en les décalant de 1 car on en a déjà rajouté 1
					}
				}
				if (recipeAdded == false && tmp.size() < numberOfTendancies) {
					Pair<Long, Float> pairOfTheRecipe = new Pair<Long, Float>(r.getId(),recipeMean);
					tmp.add(pairOfTheRecipe);
				}
	
				tmpPair.clear();
				for (Pair c: tmp){
					tmpPair.add(c);
				}
	
			}
			for(Pair<Long, Float> el: tmpPair) {
				long id = el.getValue0();
				Recipe r = em.find(Recipe.class, id);
				tr.add(new RecipeDTO(r.getId(),r.getName(),r.getIngredients(),r.getAuthorID(),r.getRatings()));
			}
		}
		return tr;
	}
	
		@Override
		public ArrayList<RecipeDTO> getRecipeOfTheMonth(){
			ArrayList <RecipeDTO> tr = new ArrayList<RecipeDTO>();
			int numberOfTendancies = 20;
			TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
			List<Recipe> re = query.getResultList();
			ArrayList<Recipe> rl = new ArrayList<Recipe>();
			for (Recipe r: re) {
				String date = r.getDate();
				String Part[] = date.split("/");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		        Calendar cal = Calendar.getInstance();
		        Date Todaydate = cal.getTime();
		        String todaysdate = dateFormat.format(Todaydate);
		        String PartTD[] = todaysdate.split("/");
	
				if (Integer.parseInt(Part[1]) ==  Integer.parseInt(PartTD[0]) &&  Integer.parseInt(Part[2]) ==  Integer.parseInt(PartTD[2])) {
					rl.add(r);
				}
			}
			if (rl.size() > 0) {
				ArrayList<Pair<Long,Float>> tmpPair = new ArrayList<Pair<Long,Float>>(); //Array actualise de pair <id,mean>
				Recipe r1 = rl.get(0);
				float mean1 = 0;
				for(Ratings g: r1.getRatings()) {
					mean1 += g.getRate();
				}
				mean1 = mean1 / r1.getRatings().size();
				Pair<Long, Float> pairOfTheRecipe1 = new Pair<Long, Float>(r1.getId(),mean1);
				tmpPair.add(pairOfTheRecipe1);
				for(int u = 1; u < rl.size(); u++)
				{
					//On a déjà mis la première recette:
					Recipe r = rl.get(u);
					float recipeMean = 0;
					for(Ratings g: r.getRatings()) {
						recipeMean += g.getRate();
					}
					boolean recipeAdded = false;
					recipeMean = recipeMean / r.getRatings().size();
					ArrayList<Pair<Long,Float>> tmp = new ArrayList<Pair<Long,Float>>();
					int i = 0;
					boolean flag = true;
					while (flag) {
						Pair<Long, Float> p;
						p = tmpPair.get(i);
						if (recipeMean < p.getValue1()){
							tmp.add(tmpPair.get(i));
						}else {
							Pair<Long, Float> pairOfTheRecipe = new Pair<Long, Float>(r.getId(),recipeMean);
							tmp.add(pairOfTheRecipe);
							recipeAdded = true;
							flag = false;
							//i += 1;
						}
						i += 1;
						if (i == tmpPair.size()){
							flag = false;
						}
					}
				if (recipeAdded) {
						for (int k = i; k < Math.min((tmpPair.size() + 1),numberOfTendancies) ; k++ ) {
							tmp.add(tmpPair.get(k-1)); //On ajoute les éléments en les décalant de 1 car on en a déjà rajouté 1
						}
					}
					if (recipeAdded == false && tmp.size() < numberOfTendancies) {
						Pair<Long, Float> pairOfTheRecipe = new Pair<Long, Float>(r.getId(),recipeMean);
						tmp.add(pairOfTheRecipe);
					}
	
					tmpPair.clear();
					for (Pair c: tmp){
						tmpPair.add(c);
					}
	
				}
				for(Pair<Long, Float> el: tmpPair) {
					Recipe rd = em.find(Recipe.class,  el.getValue0());
					tr.add(new RecipeDTO(rd.getId(),rd.getName(),rd.getIngredients(),rd.getAuthorID(),rd.getRatings()));
				}
				
			}
		
		return tr;
	}
}

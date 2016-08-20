import org.json.JSONException;
import org.json.JSONObject;

class SyndicateModelParser {


		public SyndicateModelParser() {
		}

		public SyndicateModel parseSyndicateModel(String json_object) {

			SyndicateModel local_model = null;
			try {
					JSONObject jsobj = new JSONObject(json_object);

					local_model = new SyndicateModel(jsobj.getString("reason") , jsobj.getString("title") , jsobj.getInt("duration") , jsobj.getString("aspectRatio") , );
 			} 
			catch (JSONException e){

 				 e.printStackTrace();
			}

			return local_model;
		}
			
}
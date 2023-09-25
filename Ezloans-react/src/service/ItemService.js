import axios from "axios";

const ITEM_REST_API_URL = 'http://localhost:8085/ezloans/api/item';

class ItemService {

	static getItems() {
		try {
			return axios.get(ITEM_REST_API_URL);
		} catch (error) {
			console.error("Get Error: ", error);
		}
	}

	static createItem(item) {
        try {
            return axios.post(ITEM_REST_API_URL, item);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

	static getItemById(itemId) {
        try {
            return axios.get(ITEM_REST_API_URL + '/' + itemId);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

	static updateItem(item, itemId) {
        try {
            return axios.put(ITEM_REST_API_URL + '/' + itemId, item);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

	static deleteItem(itemId) {
        try {
            return axios.delete(ITEM_REST_API_URL + '/' + itemId);
        } catch (error) {
            console.error("Error: ", error);
        }
		
	}

    static async getDescriptions(type) {
        try {
            const res = await axios.get('http://localhost:8085/ezloans/api/getdescription/' + type)
            return res
        } catch(error) {
            console.error("Error: ", error);
        }

    }

}

export default ItemService;
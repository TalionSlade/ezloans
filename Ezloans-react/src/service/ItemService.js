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
		return axios.post(ITEM_REST_API_URL, item);
	}

	static getItemById(itemId) {
		return axios.get(ITEM_REST_API_URL + '/' + itemId);
	}

	static updateItem(item, itemId) {
        console.log("Item obj: ", item)
		return axios.put(ITEM_REST_API_URL + '/' + itemId, item);
	}

	static deleteItem(itemId) {
		return axios.delete(ITEM_REST_API_URL + '/' + itemId);
	}

	// static async searchItemByName(name) {
	// 	try {
	// 		const response = await axios.get(`${ITEM_REST_API_URL}/search?name=${name}`);
	// 		return response.data;
	// 	} catch (error) {
	// 		console.error("Error searching for Items: ", error);
	// 	}

	// }

}

export default ItemService;
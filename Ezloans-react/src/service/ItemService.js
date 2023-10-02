import axios from "axios";

const ITEM_REST_API_URL = 'http://localhost:8085/ezloans/api/item';

// Class component to interact with Item API
class ItemService {

    // getItems(): Function to send GET request to get details of all items
	static getItems() {
		try {
			return axios.get(ITEM_REST_API_URL);
		} catch (error) {
			console.error("Get Error: ", error);
		}
	}

    // createItem(item): Function to send POST request to create a new item
    // It sends an item object with the following details:
    // item = { 
    //     desc: <String>, 
    //     status: <String>, 
    //     make: <String>, 
    //     category: <String>, 
    //     valuation: <Number>
    // }
	static createItem(item) {
        try {
            return axios.post(ITEM_REST_API_URL, item);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // getItemById(itemId): Function to send GET request to fetch item details based on ItemID
	static getItemById(itemId) {
        try {
            return axios.get(ITEM_REST_API_URL + '/' + itemId);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // updateItem(item, itemId): Function to send PUT request to update details of a specific item based on ItemID
    // It sends an item object with details of all updated fields
	static updateItem(item, itemId) {
        try {
            return axios.put(ITEM_REST_API_URL + '/' + itemId, item);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // deleteItem(itemId): Function to send DELETE request to delete a specific item based on a speicific itemID
	static deleteItem(itemId) {
        try {
            return axios.delete(ITEM_REST_API_URL + '/' + itemId);
        } catch (error) {
            console.error("Error: ", error);
        }
	}

    // getDescriptions(type): Function to send GET request to fetch descriptions of all items based on item type
    // It returns a response containing list of objects with the following fields
    // {
    //     description: <String>,
    //     Valuation: <Number>,
    //     make: <String>
    // }
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
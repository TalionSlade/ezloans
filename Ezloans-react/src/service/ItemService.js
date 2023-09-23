import axios from 'axios'; 

class ItemService {
    static async addItem(item) {
        try{
            const response = await axios.post('http://localhost:8085/ezloans/api/itemregister', item);
            return response.data;
        } catch(error) {
            console.error("Add Item Error: ", error);
        };
    }
}

export default ItemService
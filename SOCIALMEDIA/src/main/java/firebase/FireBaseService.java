package firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import Model.User;

public class FireBaseService {

	private FirebaseApp firebase;
	private FirebaseAuth auth;

	public FireBaseService() {
		try {
			this.initFirebase();
			this.initAuth();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FirebaseApp getFirebase() {
		return this.firebase;
	}

	public FirebaseAuth getAuth() {
		return this.auth;
	}

	public void initAuth() {
		this.auth = FirebaseAuth.getInstance(this.firebase);
	}

	public void initFirebase() throws IOException {
		/*
		 * FileInputStream serviceAccount = new FileInputStream(
		 * "src/main/resources/strange-song-394808-firebase-adminsdk-i6wm1-237bb10752.json"
		 * );
		 */
		FileInputStream serviceAccount = new FileInputStream(
			    this.getClass().getClassLoader().getResource("strange-song-394808-firebase-adminsdk-i6wm1-237bb10752.json").getFile());

		FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://strange-song-394808-default-rtdb.firebaseio.com").build();

		this.firebase = FirebaseApp.initializeApp(options);
	}

	public UserRecord  createUserWithEmailAndPass(User user) throws FirebaseAuthException {
//		System.out.println(this.auth);
		System.out.println("Da den buoc create account tren firebase");
		CreateRequest request = new CreateRequest().setEmail(user.getGmail()).setEmailVerified(true)
				.setPassword(user.getPassword()).setDisplayName(user.getFirstName());
		System.out.println("user duoc gui"+user);
		System.out.println("authen duoc gui"+auth);
		UserRecord userRecord = this.auth.createUser(request);
		// lấy thông tin đã đăng kí 
		return userRecord;
	}

	public static void main(String[] args) throws IOException, FirebaseAuthException {
		// Khởi tạo FireBaseService

		// Lấy đối tượng FirebaseDatabase
//		FireBaseService firebaseService = new FireBaseService();
//		FirebaseApp firebase = firebaseService.getFirebase();
//		FirebaseAuth auth = firebaseService.getAuth();
		
		// Kiểm tra xem đối tượng FirebaseDatabase có null hay không
		/*
		 * if (auth != null) { // System.out.println(auth); UserRecord userRecord =
		 * auth.getUser("V7osGsJ1VAdywvtvLEKInpgKjmv2"); // See the UserRecord reference
		 * doc for the contents of userRecord.
		 * System.out.println("Successfully fetched user data: " +
		 * userRecord.getEmail()); } else { System.out.println("ket noi that bai"); }
		 */
//		firebaseService.createUserWithEmailAndPass(null);
	}
}
